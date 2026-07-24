# Changelog

## 0.0.1 — 2026-07-24

Novo cliente `com.apibrasil.sdk.ApiBrasil` cobrindo toda a plataforma APIBrasil — mesma
arquitetura, mesmos endpoints e mesmas funções das SDKs Node.js, PHP e Flutter. Release
totalmente retrocompatível: nada da interface antiga foi alterado.

### Novidades

- **Cliente central `ApiBrasil`** com módulos por produto: `whatsapp`, `evolution`, `whatsmeow`, `sms`, `dados`, `vehicles`, `fipe`, `correios`, `cep`, `geolocation`, `geomatrix`, `recognize`, `ddd`, `holidays`, `translate`, `weather`, `loterias`, `databaseIp`, `consulta` (créditos), `ura`, `chipVirtual`, `bulk`, `auth` (login/2FA), `devices`, `catalog`, `account`, `payments` (PIX/boleto/cartão), `ipWhitelist`, `bearerRateLimit`, `reports`.
- **Transporte plugável** (`Transport`): `JdkHttpTransport` por padrão (sem dependências, `java.net.http`), `ApacheHttpTransport` sobre HttpClient 5, e injeção de implementações próprias para proxies e mocks.
- **Retry com backoff exponencial** (padrão: HTTP 429 e falhas de conexão; nunca timeouts nem erros de negócio) com suporte a `Retry-After`.
- **Hooks de observabilidade**: `onRequest`, `onResponse`, `onRetry`.
- **Hierarquia de erros**: `ValidationException`, `AuthenticationException`, `InsufficientBalanceException`, `PermissionException`, `NotFoundException`, `RateLimitException`, `ServerException`, `NetworkException`, `TimeoutException` — todas estendendo `ApiBrasilException` (unchecked).
- **Variáveis de ambiente**: `APIBRASIL_BEARER_TOKEN`, `APIBRASIL_DEVICE_TOKEN`, `APIBRASIL_SECRET_KEY`, `APIBRASIL_BASE_URL` lidas automaticamente.
- **Catálogo gerado** (`mvn -Pcodegen exec:java`): `Catalog.WhatsAppActions`, `EvolutionPaths`, `WhatsMeowActions`, `ConsultaServicos`, `ConsultaTipos` (210 tipos), `SERVICE_ACTIONS` e `CONSULTA_TIPOS_INFO`.
- **Ergonomia**: `Json.of(...)` para montar corpos, `Json.to(...)` para mapear em POJOs, e os envelopes `DeviceResponse` / `CreditResponse` para leitura tipada das respostas.
- **Testes** unitários com transporte fake (78 casos, cobrindo todas as rotas e todo o catálogo) e de contrato opcionais (`mvn -Pcontract test`).

### Compatibilidade

- A interface legada (`com.apibrasil.sdk.client.*`, `com.apibrasil.sdk.dto.*`, `ClientFactory`, `ApiException`) continua funcionando com o **mesmo contrato** — DTOs tipados e `ApiException` checada. Está marcada como deprecated: prefira `new ApiBrasil(...)`.
- `groupId`/`artifactId` passaram a ser `br.com.apibrasil:apigratis-sdk-java` (antes `com.apibrasil:sdk`). O `groupId` acompanha o dominio `apibrasil.com.br`, exigencia do Maven Central; os pacotes Java continuam em `com.apibrasil.sdk.*`.

### Notas

- Requer **Java 17+**.
- No cliente novo o timeout é um `java.time.Duration` (há `timeoutMillis(...)` para paridade com as SDKs Node/PHP).
- As respostas do cliente novo são `Map<String, Object>`; a interface legada continua devolvendo DTOs.

## 0.0.x

Clientes tipados por endpoint (`LoginClient`, `CepClient`, `BairrosClient`,
`CidadesClient`, `CidadesDDDClient`, `EstadosClient`) sobre Apache HttpClient.
