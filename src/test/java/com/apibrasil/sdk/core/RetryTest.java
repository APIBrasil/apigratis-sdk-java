package com.apibrasil.sdk.core;

import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.core.errors.NetworkException;
import com.apibrasil.sdk.core.errors.RateLimitException;
import com.apibrasil.sdk.core.errors.ServerException;
import com.apibrasil.sdk.core.errors.TimeoutException;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Política de retry")
class RetryTest {

    private static final String BASE_URL = "https://gateway.apibrasil.io/api/v2";

    @Test
    @DisplayName("padrão: 2 tentativas extras, backoff de 300ms a 5s, só em 429")
    void defaults() {
        assertEquals(2, RetryConfig.DEFAULT.retries());
        assertEquals(Duration.ofMillis(300), RetryConfig.DEFAULT.minDelay());
        assertEquals(Duration.ofSeconds(5), RetryConfig.DEFAULT.maxDelay());
        assertEquals(List.of(429), RetryConfig.DEFAULT.retryOnStatuses());
    }

    @Test
    @DisplayName("repete em HTTP 429 até o limite")
    void retriesOnRateLimit() {
        FakeTransport transport = new FakeTransport();
        transport.respondWith(
                FakeTransport.httpError(429, Map.of("message", "Calma")),
                FakeTransport.httpError(429, Map.of("message", "Calma")),
                FakeTransport.ok(Map.of("ok", true)));

        ApiBrasil api = client(transport, fastRetry(2));
        api.catalog.status();

        assertEquals(3, transport.count());
    }

    @Test
    @DisplayName("desiste depois de esgotar as tentativas")
    void givesUpAfterRetries() {
        FakeTransport transport = new FakeTransport();
        transport.setFallback(FakeTransport.httpError(429, Map.of("message", "Calma")));

        ApiBrasil api = client(transport, fastRetry(1));

        assertThrows(RateLimitException.class, () -> api.catalog.status());
        assertEquals(2, transport.count());
    }

    @Test
    @DisplayName("respeita o Retry-After do servidor")
    void honoursRetryAfter() {
        FakeTransport transport = new FakeTransport();
        transport.respondWith(
                FakeTransport.httpError(429, Map.of(), Map.of("retry-after", "0")),
                FakeTransport.ok(Map.of("ok", true)));

        List<Duration> delays = new ArrayList<>();
        ApiBrasil api = ApiBrasil.builder()
                .baseUrl(BASE_URL)
                .transport(transport)
                .retry(fastRetry(2))
                .hooks(Hooks.builder().onRetry(info -> delays.add(info.delay())).build())
                .build();

        api.catalog.status();

        assertEquals(List.of(Duration.ZERO), delays);
    }

    @Test
    @DisplayName("repete em falha de rede")
    void retriesOnNetworkFailure() {
        FakeTransport transport = new FakeTransport();
        transport.respondWith(
                new NetworkException("conexão caiu"),
                FakeTransport.ok(Map.of("ok", true)));

        ApiBrasil api = client(transport, fastRetry(2));
        api.catalog.status();

        assertEquals(2, transport.count());
    }

    @Test
    @DisplayName("NÃO repete em timeout — evita duplicar cobranças e envios")
    void neverRetriesOnTimeout() {
        FakeTransport transport = new FakeTransport();
        transport.setFallback(FakeTransport.ok(Map.of("ok", true)));
        transport.respondWith(new TimeoutException("estourou"));

        ApiBrasil api = client(transport, fastRetry(3));

        assertThrows(TimeoutException.class, () -> api.catalog.status());
        assertEquals(1, transport.count());
    }

    @Test
    @DisplayName("NÃO repete em erro de negócio (5xx fora da lista)")
    void neverRetriesOnServerErrorByDefault() {
        FakeTransport transport = new FakeTransport();
        transport.setFallback(FakeTransport.httpError(500, Map.of()));

        ApiBrasil api = client(transport, fastRetry(3));

        assertThrows(ServerException.class, () -> api.catalog.status());
        assertEquals(1, transport.count());
    }

    @Test
    @DisplayName("retryOnStatuses customizado")
    void customRetryStatuses() {
        FakeTransport transport = new FakeTransport();
        transport.respondWith(
                FakeTransport.httpError(503, Map.of()),
                FakeTransport.ok(Map.of("ok", true)));

        ApiBrasil api = client(transport, RetryConfig.builder()
                .retries(2)
                .minDelay(Duration.ofMillis(1))
                .maxDelay(Duration.ofMillis(5))
                .retryOnStatuses(429, 503)
                .build());

        api.catalog.status();

        assertEquals(2, transport.count());
    }

    @Test
    @DisplayName("RetryConfig.DISABLED desliga o retry")
    void disabled() {
        FakeTransport transport = new FakeTransport();
        transport.setFallback(FakeTransport.httpError(429, Map.of()));

        ApiBrasil api = client(transport, RetryConfig.DISABLED);

        assertThrows(RateLimitException.class, () -> api.catalog.status());
        assertEquals(1, transport.count());
    }

    @Test
    @DisplayName("backoff exponencial com jitter, limitado pelo teto")
    void backoff() {
        RetryConfig retry = RetryConfig.builder()
                .minDelay(Duration.ofMillis(100))
                .maxDelay(Duration.ofMillis(1000))
                .build();

        for (int attempt = 0; attempt < 8; attempt++) {
            Duration delay = Retry.backoffDelay(attempt, retry);
            assertTrue(delay.toMillis() >= 0, "delay não negativo");
            assertTrue(delay.toMillis() <= 1000, "delay dentro do teto: " + delay);
        }

        Duration first = Retry.backoffDelay(0, retry);
        assertTrue(first.toMillis() >= 50 && first.toMillis() <= 100,
                "primeira tentativa entre 50ms e 100ms: " + first);
    }

    private static RetryConfig fastRetry(int retries) {
        return RetryConfig.builder()
                .retries(retries)
                .minDelay(Duration.ofMillis(1))
                .maxDelay(Duration.ofMillis(5))
                .build();
    }

    private static ApiBrasil client(FakeTransport transport, RetryConfig retry) {
        return ApiBrasil.builder()
                .baseUrl(BASE_URL)
                .transport(transport)
                .retry(retry)
                .build();
    }
}
