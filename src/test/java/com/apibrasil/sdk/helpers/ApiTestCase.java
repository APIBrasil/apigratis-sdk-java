package com.apibrasil.sdk.helpers;

import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.core.ApiBrasilConfig;
import com.apibrasil.sdk.core.RetryConfig;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Base dos testes unitários: cria um {@link ApiBrasil} sobre o
 * {@link FakeTransport}, sem rede e sem retry.
 */
public abstract class ApiTestCase {

    protected FakeTransport transport;
    protected ApiBrasil api;

    /** Base fixa nos testes — imune a {@code APIBRASIL_BASE_URL} no ambiente. */
    protected static final String BASE_URL = "https://gateway.apibrasil.io/api/v2";

    @BeforeEach
    protected void setUpClient() {
        transport = new FakeTransport();
        api = new ApiBrasil(ApiBrasilConfig.builder()
                .bearerToken("bearer-de-teste")
                .deviceToken("device-de-teste")
                .baseUrl(BASE_URL)
                .transport(transport)
                .retry(RetryConfig.DISABLED)
                .build());
    }

    /** Confere verbo e caminho (relativo à base) da última requisição. */
    protected void assertCall(String method, String path) {
        assertEquals(method, transport.lastMethod(), "verbo HTTP");
        assertEquals(BASE_URL + path, transport.lastUrl(), "URL");
    }
}
