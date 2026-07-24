#Requires -Version 7.0
<#
.SYNOPSIS
    Publica a SDK no Maven Central (central.sonatype.com).

.DESCRIPTION
    Faz as verificacoes chatas antes de deployar: Maven e GPG no PATH, chave secreta
    disponivel, credenciais do Central no settings.xml, versao valida e arvore git limpa.
    Depois roda os testes e publica com o profile 'release' do pom.

    Versao publicada no Central e IMUTAVEL. Use -DryRun antes do primeiro release real.

.PARAMETER Version
    Grava esta versao no pom antes de publicar (ex: 0.1.1). Sem isso, usa a versao atual.

.PARAMETER DryRun
    Empacota, assina e valida tudo localmente, mas NAO envia nada para o Sonatype.

.PARAMETER SkipTests
    Pula a etapa de testes. Use so quando ja rodou os testes agora ha pouco.

.PARAMETER Tag
    Depois de publicar, cria a tag git v<versao> e faz push.

.EXAMPLE
    .\publish.ps1 -DryRun
    Ensaio completo: assina os artefatos e para antes do envio.

.EXAMPLE
    .\publish.ps1 -Version 0.1.1 -Tag
    Sobe a versao, publica no Central e marca a tag v0.1.1 no git.
#>
[CmdletBinding()]
param(
    [string] $Version,
    [switch] $DryRun,
    [switch] $SkipTests,
    [switch] $Tag
)

$ErrorActionPreference = 'Stop'
Set-StrictMode -Version Latest
# Os exit codes de mvn/gpg/git sao tratados na mao, com mensagem propria.
$PSNativeCommandUseErrorActionPreference = $false

$RepoRoot = $PSScriptRoot
$SettingsPath = Join-Path $HOME '.m2\settings.xml'

function Write-Step { param([string] $Message) Write-Host "`n==> $Message" -ForegroundColor Cyan }
function Write-Ok   { param([string] $Message) Write-Host "    OK  $Message" -ForegroundColor Green }
function Write-Warn { param([string] $Message) Write-Host "    !   $Message" -ForegroundColor Yellow }
function Fail {
    param([string] $Message, [string] $Hint)
    Write-Host "`nERRO: $Message" -ForegroundColor Red
    if ($Hint) { Write-Host "      $Hint" -ForegroundColor DarkGray }
    exit 1
}

# ---------------------------------------------------------------- ferramentas

# Um terminal aberto antes de instalar o JDK/Maven nao enxerga as variaveis novas.
# Recarrega do registro para nao obrigar a reabrir o terminal.
foreach ($name in @('JAVA_HOME', 'MAVEN_HOME')) {
    if (-not [Environment]::GetEnvironmentVariable($name, 'Process')) {
        $value = [Environment]::GetEnvironmentVariable($name, 'User')
        if ($value) { Set-Item -Path "Env:\$name" -Value $value }
    }
}
$env:Path = (@(
    $env:Path -split ';'
    [Environment]::GetEnvironmentVariable('Path', 'Machine') -split ';'
    [Environment]::GetEnvironmentVariable('Path', 'User') -split ';'
) | Where-Object { $_ } | Select-Object -Unique) -join ';'

Write-Step 'Verificando ferramentas'

$mvn = $null
$mvnCommand = Get-Command mvn -ErrorAction SilentlyContinue
if ($mvnCommand) { $mvn = $mvnCommand.Source }
if (-not $mvn -and $env:MAVEN_HOME) {
    $candidate = Join-Path $env:MAVEN_HOME 'bin\mvn.cmd'
    if (Test-Path $candidate) { $mvn = $candidate }
}
if (-not $mvn) {
    Fail 'Maven nao encontrado no PATH.' 'Baixe o binario em https://maven.apache.org/download.cgi, extraia e defina MAVEN_HOME (o winget nao tem pacote do Maven).'
}
Write-Ok "maven -> $mvn"

if (-not (Get-Command java -ErrorAction SilentlyContinue) -and -not $env:JAVA_HOME) {
    Fail 'JDK nao encontrado (java fora do PATH e JAVA_HOME vazio).' 'Baixe o JDK 17+ em https://adoptium.net e defina JAVA_HOME.'
}
Write-Ok "jdk   -> $(if ($env:JAVA_HOME) { $env:JAVA_HOME } else { (Get-Command java).Source })"

$gpg = $null
$gpgCommand = Get-Command gpg -ErrorAction SilentlyContinue
if ($gpgCommand) { $gpg = $gpgCommand.Source }
if (-not $gpg) {
    # Fora do PATH: procura nas instalacoes conhecidas. O Git for Windows traz um
    # GnuPG completo, mas o diretorio dele nao entra no PATH de proposito (sobrescreve
    # find.exe, sort.exe e cia) — por isso o caminho vai explicito para o Maven.
    $gpg = @(
        "$env:ProgramFiles\Gpg4win\bin\gpg.exe"
        "$env:ProgramFiles\GnuPG\bin\gpg.exe"
        "${env:ProgramFiles(x86)}\GnuPG\bin\gpg.exe"
        "$env:ProgramFiles\Git\usr\bin\gpg.exe"
        "${env:ProgramFiles(x86)}\Git\usr\bin\gpg.exe"
    ) | Where-Object { Test-Path $_ } | Select-Object -First 1
}
if (-not $gpg) {
    Fail 'GPG nao encontrado.' 'Instale com: winget install GnuPG.GnuPG   (aceite o prompt de UAC e reabra o terminal)'
}
Write-Ok "gpg   -> $gpg"

$secretKeys = & $gpg --list-secret-keys --keyid-format=short 2>$null | Out-String
if ($LASTEXITCODE -ne 0 -or -not ($secretKeys -match 'sec')) {
    Fail 'Nenhuma chave GPG secreta encontrada.' @"
Crie a chave e publique no keyserver (o Central so aceita assinatura verificavel):

  & '$gpg' --full-generate-key     # RSA 4096, sem expiracao, com o e-mail do projeto
  & '$gpg' --list-secret-keys --keyid-format=short
  & '$gpg' --keyserver keyserver.ubuntu.com --send-keys SEU_FINGERPRINT

(troque SEU_FINGERPRINT pela linha de 40 caracteres abaixo do 'sec' — sem < >,
 que o PowerShell trata como operador)
"@
}
$keyIds = [regex]::Matches($secretKeys, '(?m)^sec\s+\S+/(\w+)') | ForEach-Object { $_.Groups[1].Value }
Write-Ok "chave GPG -> $($keyIds -join ', ')"

# ------------------------------------------------------------- credenciais

Write-Step 'Verificando credenciais do Sonatype Central'

$credentialsHint = @'
Crie o arquivo com o User Token gerado em https://central.sonatype.com:

  <settings>
    <servers>
      <server>
        <id>central</id>
        <username>TOKEN_USERNAME</username>
        <password>TOKEN_PASSWORD</password>
      </server>
    </servers>
  </settings>
'@

$centralServer = $null
$credentialsProblem = $null

if (-not (Test-Path $SettingsPath)) {
    $credentialsProblem = "settings.xml nao encontrado em $SettingsPath."
} else {
    [xml] $settings = Get-Content $SettingsPath -Raw
    # SelectNodes em vez de $settings.settings.servers.server: nao estoura com
    # StrictMode quando o arquivo nao tem a secao <servers>.
    $servers = @($settings.SelectNodes('/settings/servers/server'))
    $centralServer = $servers | Where-Object { $_.id -eq 'central' } | Select-Object -First 1
    if (-not $centralServer) {
        $found = if ($servers.Count) { ($servers | ForEach-Object { "'$($_.id)'" }) -join ', ' } else { 'nenhum' }
        $credentialsProblem = "Nenhum <server> com <id>central</id> em $SettingsPath (ids encontrados: $found)."
    }
}

if ($credentialsProblem) {
    # O ensaio nao fala com o Sonatype, entao aqui e so aviso.
    if (-not $DryRun) { Fail $credentialsProblem $credentialsHint }
    Write-Warn $credentialsProblem
    Write-Warn 'O -DryRun nao usa as credenciais; seguindo. Resolva antes de publicar de verdade.'
} else {
    Write-Ok "settings.xml -> server 'central' (usuario $($centralServer.username))"
}

# ------------------------------------------------------------------ versao

Write-Step 'Preparando a versao'

if ($Version) {
    if ($Version -notmatch '^\d+\.\d+\.\d+(-\S+)?$') {
        Fail "Versao '$Version' fora do padrao semver (ex: 0.1.1)."
    }
    & $mvn -B -q versions:set "-DnewVersion=$Version" -DgenerateBackupPoms=false
    if ($LASTEXITCODE -ne 0) { Fail 'Falha ao gravar a nova versao no pom.' }
    Write-Ok "pom.xml atualizado para $Version"
}

[xml] $pom = Get-Content (Join-Path $RepoRoot 'pom.xml') -Raw
$groupId = $pom.project.groupId
$artifactId = $pom.project.artifactId
$pomVersion = $pom.project.version

if ($pomVersion -like '*SNAPSHOT*') {
    Fail "A versao do pom e $pomVersion." 'O Central so aceita releases. Use -Version <x.y.z>.'
}
Write-Ok "artefato -> ${groupId}:${artifactId}:${pomVersion}"

# --------------------------------------------------------------------- git

Write-Step 'Verificando o repositorio'

$dirty = @(git -C $RepoRoot status --porcelain)
if ($dirty.Count -gt 0) {
    Write-Warn 'Ha alteracoes nao commitadas:'
    $dirty | Select-Object -First 10 | ForEach-Object { Write-Host "        $_" -ForegroundColor DarkGray }
    if ((Read-Host '    Publicar mesmo assim? (s/N)') -notin @('s', 'S')) { exit 1 }
} else {
    Write-Ok 'arvore limpa'
}

$existingTag = git -C $RepoRoot tag --list "v$pomVersion"
if ($existingTag -and -not $DryRun) {
    Write-Warn "a tag v$pomVersion ja existe — essa versao pode ja ter sido publicada"
}

# -------------------------------------------------------------- passphrase

Write-Step 'Passphrase da chave GPG'

if ($env:MAVEN_GPG_PASSPHRASE) {
    Write-Ok 'lida de $env:MAVEN_GPG_PASSPHRASE'
} else {
    $secure = Read-Host '    Passphrase (fica so nesta sessao)' -AsSecureString
    $env:MAVEN_GPG_PASSPHRASE = ConvertFrom-SecureString $secure -AsPlainText
    if (-not $env:MAVEN_GPG_PASSPHRASE) { Fail 'Passphrase vazia.' }
}

# Confere a passphrase assinando um arquivo descartavel — barato, e evita
# descobrir que ela esta errada so depois de um build inteiro.
$probe = Join-Path ([IO.Path]::GetTempPath()) 'apibrasil-gpg-probe.txt'
Set-Content -Path $probe -Value 'probe' -Encoding ascii
Remove-Item "$probe.asc" -Force -ErrorAction SilentlyContinue
$env:MAVEN_GPG_PASSPHRASE | & $gpg --batch --yes --pinentry-mode loopback --passphrase-fd 0 `
    --armor --detach-sign --output "$probe.asc" $probe 2>$null
$probeFailed = $LASTEXITCODE -ne 0
Remove-Item $probe, "$probe.asc" -Force -ErrorAction SilentlyContinue
if ($probeFailed) {
    Remove-Item Env:\MAVEN_GPG_PASSPHRASE -ErrorAction SilentlyContinue
    Fail 'O GPG recusou a passphrase (assinatura de teste falhou).' @"
Confira digitando de novo. Se tiver certeza de que esta certa, o problema pode ser
acento/cedilha: a passphrase foi criada no pinentry e esta sendo digitada no
PowerShell, que trata teclas mortas de forma diferente. Troque por uma so com ASCII:

  & '$gpg' --change-passphrase $($keyIds[0])
"@
}
Write-Ok 'passphrase confere'

try {
    # ----------------------------------------------------------------- testes

    if (-not $SkipTests) {
        Write-Step 'Rodando os testes'
        & $mvn -B clean test
        if ($LASTEXITCODE -ne 0) { Fail 'Os testes falharam. Nada foi publicado.' }
        Write-Ok 'testes passaram'
    }

    # -------------------------------------------------------------- publicacao

    if ($DryRun) {
        Write-Step 'Ensaio (-DryRun): empacotando e assinando, sem enviar'
        & $mvn -B -Prelease -DskipTests "-Dgpg.executable=$gpg" clean verify
        if ($LASTEXITCODE -ne 0) { Fail 'O ensaio falhou.' }

        $signed = @(Get-ChildItem (Join-Path $RepoRoot 'target') -Filter '*.asc' -ErrorAction SilentlyContinue)
        Write-Host ''
        Write-Ok "$($signed.Count) assinatura(s) geradas em target/"
        $signed | ForEach-Object { Write-Host "        $($_.Name)" -ForegroundColor DarkGray }
        Write-Host "`nEnsaio concluido. Rode sem -DryRun para publicar de verdade." -ForegroundColor Green
        exit 0
    }

    Write-Host ''
    Write-Host "Voce esta prestes a publicar ${groupId}:${artifactId}:${pomVersion} no Maven Central." -ForegroundColor Yellow
    Write-Host 'Isso e PERMANENTE: a versao nao pode ser sobrescrita nem removida.' -ForegroundColor Yellow
    if ((Read-Host "Digite a versao para confirmar") -ne $pomVersion) {
        Fail 'Confirmacao nao conferiu. Nada foi publicado.'
    }

    Write-Step 'Publicando no Maven Central (pode levar alguns minutos)'
    & $mvn -B -Prelease -DskipTests "-Dgpg.executable=$gpg" clean deploy
    if ($LASTEXITCODE -ne 0) {
        Fail 'O deploy falhou.' 'Veja o status do envio em https://central.sonatype.com/publishing/deployments'
    }
}
finally {
    Remove-Item Env:\MAVEN_GPG_PASSPHRASE -ErrorAction SilentlyContinue
}

Write-Ok 'publicado'

# --------------------------------------------------------------------- tag

if ($Tag) {
    Write-Step 'Criando a tag no git'
    if ($existingTag) {
        Write-Warn "a tag v$pomVersion ja existe, mantendo como esta"
    } else {
        git -C $RepoRoot tag -a "v$pomVersion" -m "Release $pomVersion"
        git -C $RepoRoot push origin "v$pomVersion"
        Write-Ok "tag v$pomVersion criada e enviada"
    }
}

Write-Host ""
Write-Host "Pronto! ${groupId}:${artifactId}:${pomVersion}" -ForegroundColor Green
Write-Host "  Status : https://central.sonatype.com/publishing/deployments"
Write-Host "  Artefato: https://repo1.maven.org/maven2/$($groupId -replace '\.', '/')/$artifactId/$pomVersion/"
Write-Host "  (leva ~10-30 min para aparecer, e algumas horas para indexar na busca)"
Write-Host ""
Write-Host "Gradle : implementation '${groupId}:${artifactId}:${pomVersion}'" -ForegroundColor DarkGray
