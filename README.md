# SDK Java - APIGratis by API BRASIL 🚀

SDK oficial Java da plataforma [APIBrasil](https://apibrasil.com.br) — WhatsApp, SMS, consultas de CPF/CNPJ, veículos, CEP, correios, pagamentos PIX/boleto e muito mais.

[![Java CI with Maven](https://github.com/APIBrasil/apigratis-sdk-java/actions/workflows/maven.yml/badge.svg)](https://github.com/APIBrasil/apigratis-sdk-java/actions/workflows/maven.yml)
<a href="https://github.com/APIBrasil/apigratis-sdk-java/issues" target="_blank"><img alt="GitHub issues" src="https://img.shields.io/github/issues/APIBrasil/apigratis-sdk-java"></a>
<a href="https://github.com/APIBrasil/apigratis-sdk-java/network" target="_blank"><img alt="GitHub forks" src="https://img.shields.io/github/forks/APIBrasil/apigratis-sdk-java"></a>
<a href="https://github.com/APIBrasil/apigratis-sdk-java/stargazers" target="_blank"><img alt="GitHub stars" src="https://img.shields.io/github/stars/APIBrasil/apigratis-sdk-java"></a>
[![Minimum Java Version](https://img.shields.io/badge/java-%3E%3D%2017-orange.svg?style=flat-square)](https://adoptium.net/)
[![license mit](https://img.shields.io/badge/license-MIT-green.svg?style=flat-square)](LICENSE)

## Canais de suporte (Comunidade)

[![WhatsApp Group](https://img.shields.io/badge/WhatsApp-Group-25D366?logo=whatsapp)](https://chat.whatsapp.com/EeAWALQb6Ga5oeTbG7DD2k)
[![Telegram Group](https://img.shields.io/badge/Telegram-Group-32AFED?logo=telegram)](https://t.me/apigratisoficial)

## Instalação

Maven:

```xml
<dependency>
    <groupId>br.com.apibrasil</groupId>
    <artifactId>apigratis-sdk-java</artifactId>
    <version>0.1.0</version>
</dependency>
```

Gradle:

```groovy
implementation 'br.com.apibrasil:apigratis-sdk-java:0.1.0'
```

Requer **Java >= 17**. O transporte padrão é o `java.net.http.HttpClient` do próprio JDK — a única dependência obrigatória é o Jackson (JSON).

Obtenha suas credenciais em https://apibrasil.com.br

## Começando

```java
import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.core.Json;

import java.util.Map;

public class Exemplo {
    public static void main(String[] args) {
        ApiBrasil api = ApiBrasil.builder()
                .bearerToken(System.getenv("APIBRASIL_BEARER_TOKEN")) // JWT do login
                .deviceToken(System.getenv("APIBRASIL_DEVICE_TOKEN")) // device dos serviços device-based
                .build();

        // WhatsApp
        api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá! 👋"));

        // Consulta CNPJ (por créditos)
        Map<String, Object> empresa = api.consulta.cnpj(Json.of("cnpj", "00000000000000"));
        System.out.println(empresa.get("data"));
    }
}
```

As credenciais também podem vir só do ambiente — `new ApiBrasil()` lê automaticamente
`APIBRASIL_BEARER_TOKEN`, `APIBRASIL_DEVICE_TOKEN`, `APIBRASIL_SECRET_KEY` e `APIBRASIL_BASE_URL`.

Todas as respostas são devolvidas como **`Map<String, Object>`** já decodificado. Use `Json.of(...)` para montar os corpos e, se quiser tipar, `Json.to(resposta, MinhaClasse.class)`.

Também é possível autenticar por email/senha — o token retornado fica guardado no cliente:

```java
ApiBrasil api = new ApiBrasil();
api.auth.login(Json.of("email", "voce@empresa.com.br", "password", "******"));

// contas com 2FA:
Map<String, Object> session = api.auth.login(Json.of("email", email, "password", senha));
if (Json.bool(session, "requires_2fa")) {
    String challenge = Json.string(session, "challenge");
    api.auth.send2fa(Json.of("challenge", challenge, "method", "email"));
    api.auth.verify2fa(Json.of("challenge", challenge, "code", "000000"));
}

// ou, em uma tacada só (lança exceção se a conta exigir 2FA):
ApiBrasil.LoginResult result = ApiBrasil.login(Json.of("email", email, "password", senha));
ApiBrasil autenticado = result.client();
```

## Como a plataforma funciona

A API Brasil tem duas famílias de serviços:

| Família          | Autenticação                                   | Exemplos                                                                     |
| ---------------- | ---------------------------------------------- | ---------------------------------------------------------------------------- |
| **Device-based** | `Authorization: Bearer` + header `DeviceToken` | WhatsApp, SMS, veículos, CEP, correios, DDD, feriados, tradução, clima, OCR  |
| **Por créditos** | apenas `Authorization: Bearer` (debita saldo)  | `consulta.cpf`, `consulta.cnpj`, `consulta.veiculos`, Serasa, CNH, telefone   |

Para os serviços device-based, crie um device com a `SecretKey` da API desejada (painel APIBrasil) e use o `device_token` retornado:

```java
Map<String, Object> device = api.devices.store(
        Json.of("device_name", "meu-bot", "type", "server"),
        RequestOptions.secretKey("SUA_SECRET_KEY"));

api.setDeviceToken(Json.string(Json.object(device, "device"), "device_token"));
```

## Serviços disponíveis

| Módulo                                                          | Descrição                                                                                        |
| --------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| `api.whatsapp`                                                   | WhatsApp: `start`, `qrcode`, `sendText`, `sendFile`, `sendAudio`, `sendVideo`, fila (`queue`)...  |
| `api.evolution`                                                  | Evolution API: `sendText`, `createInstance`, `request(controller, action, body)`                  |
| `api.whatsmeow`                                                  | WhatsMeow: `sendText`, `createInstance`, `request(action, body)`                                  |
| `api.sms`                                                        | SMS device-based (`send`) e por créditos (`sendWithCredits`)                                     |
| `api.dados`                                                      | Dados cadastrais device-based (`cpf`, `cnpj`, `listaSocios`, `capitalSocial`)                    |
| `api.vehicles`                                                   | Veículos por placa (`dados`, `fipe`, `baseDados`)                                                |
| `api.fipe`                                                       | Tabela FIPE (`consultarMarcas`, `consultarModelos`, `request(action, body)`)                     |
| `api.correios`                                                   | Correios (`rastreio`)                                                                            |
| `api.cep`                                                        | CEP + geolocalização (`cep`, `bairros`, `cidades`, `estados`, `calcularDistancia`)               |
| `api.geolocation` / `api.geomatrix`                              | Geolocalização e matriz de distâncias                                                            |
| `api.recognize`                                                  | OCR / Google Vision (`base64`, `uri`)                                                            |
| `api.ddd` / `api.holidays` / `api.translate` / `api.weather`     | DDD, feriados, tradução, clima                                                                   |
| `api.loterias`                                                   | Loterias (`latest`, `resultado`)                                                                 |
| `api.databaseIp`                                                 | GeoIP (`ip`)                                                                                     |
| `api.consulta`                                                   | Consultas por créditos: `cpf`, `cnpj`, `cep`, `veiculos`, `telefone`, `generic(servico, body)`   |
| `api.ura` / `api.chipVirtual`                                    | URA reversa e chip virtual                                                                       |
| `api.bulk`                                                       | Execução em lote (`create`, `status`, `list`)                                                    |
| `api.auth`                                                       | Login, 2FA, cadastro, recuperação de senha, perfil                                               |
| `api.devices`                                                    | CRUD de devices                                                                                  |
| `api.catalog`                                                    | Catálogo de APIs, planos, documentações, servidores                                              |
| `api.account`                                                    | Saldo, faturas, notificações, tickets                                                            |
| `api.payments`                                                   | Recargas e pagamentos PIX/boleto/cartão (Santander, Inter, Mercado Pago, Sicoob)                 |
| `api.ipWhitelist` / `api.bearerRateLimit`                        | Segurança da conta                                                                               |
| `api.reports`                                                    | Relatórios e dashboard de consumo                                                                |

### WhatsApp

```java
// iniciar sessão e obter QR Code
api.whatsapp.start(Json.of("webhook_wh_message", "https://seu-webhook.com/mensagens"));

Map<String, Object> qr = api.whatsapp.qrcode();
System.out.println(Json.object(qr, "response").get("qrcode")); // data URI base64

// envios
api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!"));
api.whatsapp.sendFile(Json.of("number", "5511999999999", "path", "https://exemplo.com/nota.pdf"));
api.whatsapp.sendAudio(Json.of("number", "5511999999999", "path", "https://exemplo.com/audio.mp3"));

// qualquer action da documentação, inclusive via fila
api.whatsapp.request("sendLocation", Json.of("number", "5511999999999", "lat", -23.5, "lng", -46.6));
api.whatsapp.queue("sendText", Json.of("number", "5511999999999", "text", "assíncrono 🚀"));
```

O envelope device-based (`error`, `message`, `response`, `api_limit`) tem uma leitura tipada opcional:

```java
DeviceResponse res = DeviceResponse.of(
        api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!")));

if (!res.isError()) {
    System.out.println(res.response());
}
```

### Consultas por créditos

```java
// CPF / CNPJ
Map<String, Object> cpf = api.consulta.cpf(Json.of("cpf", "00000000000"));
Map<String, Object> socios = api.consulta.cnpj(Json.of("cnpj", "00000000000000", "tipo", "lista-socios"));

// veicular
Map<String, Object> veiculo = api.consulta.veiculos(Json.of("placa", "ABC1234"));

// qualquer produto do catálogo
Map<String, Object> score = api.consulta.generic("cpf",
        Json.of("cpf", "00000000000", "tipo", "serasa-score-pf"));

// homologação (sandbox, sem cobrança)
Map<String, Object> teste = api.consulta.cpf(Json.of("cpf", "00000000000", "homolog", true));

// envelope tipado (balance, tax, valor_consulta, data)
CreditResponse res = CreditResponse.of(cpf);
System.out.println(res.balance() + " -> " + res.data());
```

### Veículos e FIPE (device-based)

```java
Map<String, Object> dados = api.vehicles.dados(Json.of("placa", "ABC1234"));
Map<String, Object> fipe = api.vehicles.fipe(Json.of("placa", "ABC1234"));
```

### SMS

```java
api.sms.send(Json.of("number", "5511999999999", "message", "Seu código: 123456"));
// ou debitando créditos da conta (sem device):
api.sms.sendWithCredits(Json.of("number", "5511999999999", "message", "Olá!"));
```

### Pagamentos e recargas

```java
Map<String, Object> pix = api.payments.pixGenerate("inter", Json.of("amount", 100));
Map<String, Object> status = api.payments.pixStatus("inter", Json.string(pix, "txId"));

Map<String, Object> boleto = api.payments.boletoGenerate("sicoob", Json.of("amount", 150));
byte[] pdf = api.payments.boletoPdf("sicoob", Json.string(boleto, "id")); // conteúdo binário
```

### Múltiplos devices

```java
ApiBrasil comercial = api.withDevice("DEVICE_TOKEN_COMERCIAL");
ApiBrasil suporte = api.withDevice("DEVICE_TOKEN_SUPORTE");

comercial.whatsapp.sendText(Json.of("number", "55...", "text", "Proposta enviada!"));
suporte.whatsapp.sendText(Json.of("number", "55...", "text", "Como posso ajudar?"));
```

## Tratamento de erros

Cada categoria de falha tem a sua própria classe — todas estendem `ApiBrasilException`
(que por sua vez estende `RuntimeException`, então não há `throws` obrigatório):

| Classe                              | Quando                                    |
| ----------------------------------- | ----------------------------------------- |
| `ValidationException`               | 400/422 — payload inválido                |
| `AuthenticationException`           | 401 — token ausente/expirado              |
| `InsufficientBalanceException`      | 402 — sem saldo/créditos                  |
| `PermissionException`               | 403 — sem permissão (ex: exige PJ)        |
| `NotFoundException`                 | 404/410 — sem dados / rota desativada     |
| `RateLimitException`                | 429 — limite atingido (`retryAfter()`)    |
| `ServerException`                   | 5xx — erro do gateway/provedor            |
| `NetworkException` / `TimeoutException` | falha antes da resposta               |

```java
import com.apibrasil.sdk.core.errors.InsufficientBalanceException;
import com.apibrasil.sdk.core.errors.RateLimitException;

try {
    api.consulta.cpf(Json.of("cpf", "00000000000"));
} catch (InsufficientBalanceException e) {
    System.out.println("Recarregue seus créditos");
} catch (RateLimitException e) {
    System.out.println("Aguarde " + e.retryAfter());
}
```

Todo erro expõe `status()` (HTTP), `errorCode()` (código da API) e `response()` (corpo completo da resposta).

## Retry e observabilidade

Por padrão a SDK refaz a chamada em **HTTP 429** e em **falhas de conexão** (2 tentativas extras, backoff exponencial com jitter, respeitando `Retry-After`). Timeouts e erros de negócio nunca são refeitos — evita duplicar cobranças e envios.

```java
ApiBrasil api = ApiBrasil.builder()
        .retry(RetryConfig.builder()
                .retries(3)
                .minDelay(Duration.ofMillis(500))
                .retryOnStatuses(429, 503)
                .build())                       // ou RetryConfig.DISABLED
        .hooks(Hooks.builder()
                .onRequest(i -> System.out.printf("→ %s %s (#%d)%n", i.method(), i.url(), i.attempt()))
                .onResponse(i -> System.out.printf("← %d em %dms%n", i.status(), i.duration().toMillis()))
                .onRetry(i -> System.out.printf("retry em %s: %s%n", i.delay(), i.reason()))
                .build())
        .build();
```

## Transporte plugável

O HTTP padrão usa o cliente do JDK, mas a interface `Transport` permite trocar a camada inteira (proxy corporativo, outro cliente, mocks de teste):

```java
// Apache HttpClient 5 (pool de conexões, proxy, SSL customizado)
ApiBrasil api = ApiBrasil.builder()
        .transport(new ApacheHttpTransport())
        .build();

// ou um java.net.http.HttpClient já configurado
ApiBrasil api = ApiBrasil.builder()
        .transport(new JdkHttpTransport(HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress("proxy.local", 3128)))
                .build()))
        .build();
```

Ou implemente o seu:

```java
final class MeuTransporte implements Transport {
    @Override
    public TransportResponse send(TransportRequest request) {
        // use o cliente HTTP que quiser e devolva status, headers e corpo
        return new TransportResponse(200, Map.of(), Map.of("ok", true));
    }
}
```

## Catálogo gerado

As actions de WhatsApp/Evolution/WhatsMeow e os 210+ `tipo` de consulta estão disponíveis
em constantes geradas do catálogo real da plataforma (`mvn -Pcodegen exec:java` atualiza):

```java
import com.apibrasil.sdk.generated.Catalog;

Catalog.WhatsAppActions.SEND_TEXT;         // "sendText"
Catalog.WhatsAppActions.ALL;               // ["sendText", "sendFile", ...]
Catalog.actionsOf("whatsmeow");            // actions documentadas do serviço
Catalog.consultaTipo("lista-socios");      // ConsultaTipoInfo[service=cnpj, fields=[cnpj]]
```

## Endpoint sem método dedicado?

Todo o gateway fica acessível pela porta de saída genérica, já com seus headers de autenticação:

```java
api.request("POST", "/consulta/cpf/credits", Json.of("cpf", "00000000000"));
api.requestJson("GET", "/reports/quick-stats", null);
```

Documentação completa dos endpoints: https://doc.apibrasil.io

## Configuração avançada

```java
ApiBrasil api = ApiBrasil.builder()
        .bearerToken("...")   // ou APIBRASIL_BEARER_TOKEN
        .deviceToken("...")   // ou APIBRASIL_DEVICE_TOKEN
        .secretKey("...")     // usada em devices.store (ou APIBRASIL_SECRET_KEY)
        .baseUrl("https://gateway.apibrasil.io/api/v2") // padrão (ou APIBRASIL_BASE_URL)
        .timeout(Duration.ofSeconds(30))
        .header("X-Custom", "valor")
        .retry(RetryConfig.DEFAULT)
        .hooks(Hooks.builder().onRetry(i -> log.warn(i.reason())).build())
        .transport(null)      // Transport customizado
        .build();
```

Opções por requisição (último parâmetro dos métodos): `query`, `headers`, `bearerToken`,
`deviceToken`, `secretKey`, `timeout`, `responseType`.

```java
api.whatsapp.sendText(
        Json.of("number", "5511999999999", "text", "Olá!"),
        RequestOptions.builder()
                .deviceToken("OUTRO_DEVICE")
                .timeout(Duration.ofSeconds(60))
                .build());
```

O cliente é seguro para uso concorrente e implementa `AutoCloseable` — feche-o (ou use
try-with-resources) quando terminar, para liberar o transporte criado pela SDK.

## Interface legada (`com.apibrasil.sdk.client`)

As classes `LoginClient`, `CepClient`, `BairrosClient`, `CidadesClient`, `EstadosClient` e
companhia continuam funcionando exatamente como antes (DTOs tipados, `ApiException`
checada), mas estão **deprecadas** — prefira o cliente `ApiBrasil`.

<details>
<summary>Exemplo da interface legada</summary>

```java
ApiClient client = ClientFactory.createDefaultClient();
LoginClient loginClient = new LoginClient(client);

LoginReq request = new LoginReq();
request.setEmail("seuemail@exemplo.com");
request.setPassword("suasenha");

LoginRes response = loginClient.login(request); // lança ApiException
```

</details>

## Licença

MIT — veja [LICENSE](LICENSE).
