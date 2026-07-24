package com.apibrasil.sdk.core;

import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.helpers.ApiTestCase;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ApiHttpClient")
class HttpClientTest extends ApiTestCase {

    @Test
    @DisplayName("monta a URL sem barras duplicadas")
    void joinsUrl() {
        assertEquals("https://x.test/api/v2/cep/cep",
                ApiHttpClient.joinUrl("https://x.test/api/v2/", "/cep/cep"));
        assertEquals("https://x.test/api/v2",
                ApiHttpClient.joinUrl("https://x.test/api/v2/", ""));
    }

    @Test
    @DisplayName("monta a query string ignorando nulos")
    void buildsQueryString() {
        String query = ApiHttpClient.buildQueryString(Json.of(
                "search", "meu device",
                "vazio", null,
                "paginate", true));

        assertEquals("?search=meu+device&paginate=true", query);
        assertEquals("", ApiHttpClient.buildQueryString(null));
        assertEquals("", ApiHttpClient.buildQueryString(Map.of()));
    }

    @Test
    @DisplayName("serializa listas de query como CSV")
    void buildsQueryStringWithList() {
        String query = ApiHttpClient.buildQueryString(Json.of("tipos", List.of("a", "b")));
        assertEquals("?tipos=a%2Cb", query);
    }

    @Test
    @DisplayName("envia o body como JSON")
    void encodesBody() {
        api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá! 👋"));

        assertEquals("{\"number\":\"5511999999999\",\"text\":\"Olá! 👋\"}", transport.lastRawBody());
    }

    @Test
    @DisplayName("body vazio vira objeto, nunca lista")
    void encodesEmptyBodyAsObject() {
        api.whatsapp.request("start", Map.of());
        assertEquals("{}", transport.lastRawBody());
    }

    @Test
    @DisplayName("requisição sem body não envia corpo")
    void noBody() {
        api.catalog.status();
        assertNull(transport.lastRawBody());
    }

    @Test
    @DisplayName("resposta vazia vira objeto vazio")
    void emptyResponse() {
        transport.respondWith(FakeTransport.ok(null));
        Map<String, Object> response = api.catalog.status();
        assertTrue(response.isEmpty());
    }

    @Test
    @DisplayName("resposta em lista é embrulhada em data")
    void listResponseIsWrapped() {
        transport.respondWith(FakeTransport.ok(List.of(1, 2, 3)));
        Map<String, Object> response = api.catalog.servers();
        assertEquals(List.of(1, 2, 3), response.get("data"));
    }

    @Test
    @DisplayName("timeout do cliente e da requisição")
    void timeouts() {
        assertEquals(ApiHttpClient.DEFAULT_TIMEOUT, transportTimeoutAfter(() -> api.catalog.status()));

        api.catalog.apis(null, RequestOptions.builder().timeoutMillis(1500).build());
        assertEquals(Duration.ofMillis(1500), transport.last().timeout());
    }

    @Test
    @DisplayName("responseType BYTES devolve o corpo cru")
    void downloadsBytes() {
        byte[] pdf = "%PDF-1.4".getBytes(StandardCharsets.UTF_8);
        transport.respondWith(FakeTransport.ok(pdf));

        byte[] result = api.payments.boletoPdf("santander", "123");

        assertArrayEquals(pdf, result);
        assertEquals(ResponseType.BYTES, transport.last().responseType());
        assertCall("GET", "/santander/boleto/123/pdf");
    }

    @Test
    @DisplayName("dispara os hooks de observabilidade")
    void firesHooks() {
        AtomicInteger requests = new AtomicInteger();
        AtomicInteger responses = new AtomicInteger();

        FakeTransport fake = new FakeTransport();
        ApiBrasil client = ApiBrasil.builder()
                .baseUrl(BASE_URL)
                .transport(fake)
                .hooks(Hooks.builder()
                        .onRequest(info -> requests.incrementAndGet())
                        .onResponse(info -> responses.incrementAndGet())
                        .build())
                .build();

        client.catalog.status();

        assertEquals(1, requests.get());
        assertEquals(1, responses.get());
    }

    @Test
    @DisplayName("headers configurados no cliente vão em todas as requisições")
    void clientHeaders() {
        FakeTransport fake = new FakeTransport();
        ApiBrasil client = ApiBrasil.builder()
                .baseUrl(BASE_URL)
                .transport(fake)
                .header("X-App", "meu-app")
                .build();

        client.catalog.status();

        assertEquals("meu-app", fake.lastHeaders().get("X-App"));
    }

    private Duration transportTimeoutAfter(Runnable call) {
        call.run();
        return transport.last().timeout();
    }
}
