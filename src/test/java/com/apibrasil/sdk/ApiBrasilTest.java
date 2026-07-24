package com.apibrasil.sdk;

import com.apibrasil.sdk.core.ApiBrasilConfig;
import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.core.RetryConfig;
import com.apibrasil.sdk.core.errors.ApiBrasilException;
import com.apibrasil.sdk.helpers.ApiTestCase;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ApiBrasil")
class ApiBrasilTest extends ApiTestCase {

    @Test
    @DisplayName("expõe todos os módulos da plataforma")
    void exposesAllServices() {
        assertNotNull(api.auth);
        assertNotNull(api.devices);
        assertNotNull(api.whatsapp);
        assertNotNull(api.evolution);
        assertNotNull(api.whatsmeow);
        assertNotNull(api.sms);
        assertNotNull(api.dados);
        assertNotNull(api.vehicles);
        assertNotNull(api.fipe);
        assertNotNull(api.correios);
        assertNotNull(api.cep);
        assertNotNull(api.geolocation);
        assertNotNull(api.geomatrix);
        assertNotNull(api.recognize);
        assertNotNull(api.ddd);
        assertNotNull(api.holidays);
        assertNotNull(api.translate);
        assertNotNull(api.weather);
        assertNotNull(api.loterias);
        assertNotNull(api.databaseIp);
        assertNotNull(api.consulta);
        assertNotNull(api.ura);
        assertNotNull(api.chipVirtual);
        assertNotNull(api.bulk);
        assertNotNull(api.catalog);
        assertNotNull(api.account);
        assertNotNull(api.payments);
        assertNotNull(api.ipWhitelist);
        assertNotNull(api.bearerRateLimit);
        assertNotNull(api.reports);
    }

    @Test
    @DisplayName("injeta os headers de autenticação da plataforma")
    void sendsAuthHeaders() {
        api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!"));

        Map<String, String> headers = transport.lastHeaders();
        assertEquals("Bearer bearer-de-teste", headers.get("Authorization"));
        assertEquals("device-de-teste", headers.get("DeviceToken"));
        assertEquals("application/json", headers.get("Content-Type"));
        assertEquals(ApiHttpClient.SDK_USER_AGENT, headers.get("User-Agent"));
    }

    @Test
    @DisplayName("setBearerToken e setDeviceToken valem para todos os serviços")
    void updatesTokens() {
        api.setBearerToken("novo-bearer").setDeviceToken("novo-device");

        api.cep.cep(Json.of("cep", "01001000"));

        assertEquals("Bearer novo-bearer", transport.lastHeaders().get("Authorization"));
        assertEquals("novo-device", transport.lastHeaders().get("DeviceToken"));
    }

    @Test
    @DisplayName("withDevice mantém as credenciais e troca só o device")
    void withDeviceKeepsCredentials() {
        ApiBrasil other = api.withDevice("outro-device");

        other.whatsapp.qrcode();

        assertEquals("Bearer bearer-de-teste", transport.lastHeaders().get("Authorization"));
        assertEquals("outro-device", transport.lastHeaders().get("DeviceToken"));
        assertSame(transport, other.http.transport(), "o transporte é compartilhado");
    }

    @Test
    @DisplayName("options sobrescrevem os tokens por requisição")
    void perRequestOverrides() {
        api.whatsapp.sendText(
                Json.of("number", "5511999999999", "text", "oi"),
                RequestOptions.builder()
                        .bearerToken("bearer-da-requisicao")
                        .deviceToken("device-da-requisicao")
                        .header("X-Trace", "abc")
                        .build());

        Map<String, String> headers = transport.lastHeaders();
        assertEquals("Bearer bearer-da-requisicao", headers.get("Authorization"));
        assertEquals("device-da-requisicao", headers.get("DeviceToken"));
        assertEquals("abc", headers.get("X-Trace"));
    }

    @Test
    @DisplayName("request() é a porta de saída genérica")
    void genericRequest() {
        api.request("POST", "/consulta/cpf/credits", Json.of("cpf", "00000000000"));

        assertCall("POST", "/consulta/cpf/credits");
        assertEquals("00000000000", transport.lastBody().get("cpf"));
    }

    @Test
    @DisplayName("login estático devolve cliente autenticado")
    void staticLogin() {
        FakeTransport fake = new FakeTransport();
        fake.respondWith(FakeTransport.ok(Map.of(
                "authorization", Map.of("token", "jwt-do-login"))));

        ApiBrasil.LoginResult result = ApiBrasil.login(
                Json.of("email", "a@b.com", "password", "123"),
                ApiBrasilConfig.builder().baseUrl(BASE_URL).transport(fake).build());

        assertEquals("jwt-do-login", result.client().http.bearerToken());
        assertNotNull(result.session());
        result.client().close();
    }

    @Test
    @DisplayName("login estático recusa contas com 2FA")
    void staticLoginWith2fa() {
        FakeTransport fake = new FakeTransport();
        fake.respondWith(FakeTransport.ok(Map.of("requires_2fa", true, "challenge", "xyz")));

        ApiBrasilException error = assertThrows(ApiBrasilException.class, () -> ApiBrasil.login(
                Json.of("email", "a@b.com", "password", "123"),
                ApiBrasilConfig.builder().baseUrl(BASE_URL).transport(fake).build()));

        assertTrue(error.getMessage().contains("dois fatores"));
    }

    @Test
    @DisplayName("close não fecha transporte injetado pelo usuário")
    void closeKeepsInjectedTransport() {
        api.close();
        assertFalse(transport.isClosed());
    }

    @Test
    @DisplayName("lê as credenciais das variáveis de ambiente")
    void readsEnvironment() {
        System.setProperty("APIBRASIL_BEARER_TOKEN", "bearer-do-ambiente");
        System.setProperty("APIBRASIL_BASE_URL", "https://exemplo.test/api/v2");
        try {
            FakeTransport fake = new FakeTransport();
            ApiBrasil client = new ApiBrasil(ApiBrasilConfig.builder().transport(fake).build());

            assertEquals("bearer-do-ambiente", client.http.bearerToken());
            assertEquals("https://exemplo.test/api/v2", client.http.baseUrl());

            client.catalog.status();
            assertEquals("https://exemplo.test/api/v2/status", fake.lastUrl());
        } finally {
            System.clearProperty("APIBRASIL_BEARER_TOKEN");
            System.clearProperty("APIBRASIL_BASE_URL");
        }
    }

    @Test
    @DisplayName("configuração explícita vence o ambiente")
    void explicitConfigWinsOverEnvironment() {
        System.setProperty("APIBRASIL_BEARER_TOKEN", "do-ambiente");
        try {
            ApiBrasil client = ApiBrasil.builder()
                    .bearerToken("explicito")
                    .transport(new FakeTransport())
                    .build();

            assertEquals("explicito", client.http.bearerToken());
        } finally {
            System.clearProperty("APIBRASIL_BEARER_TOKEN");
        }
    }

    @Test
    @DisplayName("sem token não envia Authorization")
    void noTokenNoHeader() {
        FakeTransport fake = new FakeTransport();
        ApiBrasil client = new ApiBrasil(ApiBrasilConfig.builder()
                .baseUrl(BASE_URL)
                .transport(fake)
                .retry(RetryConfig.DISABLED)
                .build());

        client.catalog.status();

        assertNull(fake.lastHeaders().get("Authorization"));
        assertNull(fake.lastHeaders().get("DeviceToken"));
    }
}
