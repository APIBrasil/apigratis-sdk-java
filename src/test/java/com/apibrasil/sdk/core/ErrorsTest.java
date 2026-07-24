package com.apibrasil.sdk.core;

import com.apibrasil.sdk.core.errors.ApiBrasilException;
import com.apibrasil.sdk.core.errors.AuthenticationException;
import com.apibrasil.sdk.core.errors.ErrorFactory;
import com.apibrasil.sdk.core.errors.InsufficientBalanceException;
import com.apibrasil.sdk.core.errors.NetworkException;
import com.apibrasil.sdk.core.errors.NotFoundException;
import com.apibrasil.sdk.core.errors.PermissionException;
import com.apibrasil.sdk.core.errors.RateLimitException;
import com.apibrasil.sdk.core.errors.ServerException;
import com.apibrasil.sdk.core.errors.TimeoutException;
import com.apibrasil.sdk.core.errors.ValidationException;
import com.apibrasil.sdk.helpers.ApiTestCase;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Hierarquia de erros")
class ErrorsTest extends ApiTestCase {

    @Test
    @DisplayName("mapeia cada status HTTP para a exceção correta")
    void mapsStatusToException() {
        assertInstanceOf(ValidationException.class, error(400));
        assertInstanceOf(AuthenticationException.class, error(401));
        assertInstanceOf(InsufficientBalanceException.class, error(402));
        assertInstanceOf(PermissionException.class, error(403));
        assertInstanceOf(NotFoundException.class, error(404));
        assertInstanceOf(NotFoundException.class, error(410));
        assertInstanceOf(ValidationException.class, error(422));
        assertInstanceOf(RateLimitException.class, error(429));
        assertInstanceOf(ServerException.class, error(500));
        assertInstanceOf(ServerException.class, error(503));
        assertEquals(ApiBrasilException.class, error(418).getClass());
    }

    @Test
    @DisplayName("usa a mensagem da API quando existir")
    void usesApiMessage() {
        transport.respondWith(FakeTransport.httpError(402, Map.of(
                "message", "Saldo insuficiente.", "code", "NO_BALANCE")));

        ApiBrasilException error = assertThrows(ApiBrasilException.class,
                () -> api.consulta.cpf(Json.of("cpf", "00000000000")));

        assertEquals("Saldo insuficiente.", error.getMessage());
        assertEquals("NO_BALANCE", error.errorCode());
        assertEquals(402, error.status());
        assertTrue(error.isInsufficientBalance());
        assertEquals("Saldo insuficiente.", error.responseAsMap().get("message"));
    }

    @Test
    @DisplayName("cai para uma mensagem padrão quando a API não manda nenhuma")
    void fallsBackToDefaultMessage() {
        transport.respondWith(FakeTransport.httpError(500, "boom"));

        ApiBrasilException error = assertThrows(ApiBrasilException.class, () -> api.catalog.status());

        assertEquals("A API respondeu com HTTP 500.", error.getMessage());
    }

    @Test
    @DisplayName("401 é reconhecido como não autorizado")
    void unauthorized() {
        transport.respondWith(FakeTransport.httpError(401, Map.of("error", "Token expirado")));

        ApiBrasilException error = assertThrows(ApiBrasilException.class, () -> api.account.balance());

        assertTrue(error.isUnauthorized());
        assertEquals("Token expirado", error.getMessage());
    }

    @Test
    @DisplayName("falhas de rede viram NetworkException")
    void networkFailure() {
        transport.respondWith(new NetworkException("conexão recusada"));

        assertThrows(NetworkException.class, () -> api.catalog.status());
    }

    @Test
    @DisplayName("timeout é uma NetworkException especializada")
    void timeoutIsNetworkException() {
        transport.respondWith(new TimeoutException("estourou"));

        NetworkException error = assertThrows(NetworkException.class, () -> api.catalog.status());
        assertInstanceOf(TimeoutException.class, error);
    }

    @Test
    @DisplayName("lê o Retry-After em segundos")
    void parsesRetryAfterSeconds() {
        assertEquals(Duration.ofSeconds(3), ErrorFactory.parseRetryAfter(Map.of("retry-after", "3")));
        assertEquals(Duration.ofMillis(1500), ErrorFactory.parseRetryAfter(Map.of("Retry-After", "1.5")));
        assertNull(ErrorFactory.parseRetryAfter(Map.of()));
        assertNull(ErrorFactory.parseRetryAfter(null));
        assertNull(ErrorFactory.parseRetryAfter(Map.of("retry-after", "nao-e-data")));
    }

    @Test
    @DisplayName("RateLimitException carrega o Retry-After")
    void rateLimitCarriesRetryAfter() {
        transport.respondWith(FakeTransport.httpError(429, Map.of("message", "Calma"),
                Map.of("retry-after", "2")));

        RateLimitException error = assertThrows(RateLimitException.class, () -> api.catalog.status());

        assertEquals(Duration.ofSeconds(2), error.retryAfter());
    }

    @Test
    @DisplayName("from() preserva erros já tipados")
    void fromKeepsTypedErrors() {
        NotFoundException original = new NotFoundException("sumiu", 404, null, null, null);
        assertEquals(original, ApiBrasilException.from(original));
    }

    private ApiBrasilException error(int status) {
        transport.respondWith(FakeTransport.httpError(status, Map.of()));
        return assertThrows(ApiBrasilException.class, () -> api.catalog.status());
    }
}
