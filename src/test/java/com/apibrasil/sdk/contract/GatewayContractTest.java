package com.apibrasil.sdk.contract;

import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.core.Env;
import com.apibrasil.sdk.core.Json;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testes de contrato contra o gateway real — não rodam na suíte padrão.
 *
 * <pre>{@code
 * APIBRASIL_CONTRACT=1 APIBRASIL_BEARER_TOKEN=... mvn -Pcontract test
 * }</pre>
 */
@EnabledIfEnvironmentVariable(named = "APIBRASIL_CONTRACT", matches = "1")
@DisplayName("Contrato do gateway")
class GatewayContractTest {

    private ApiBrasil api;

    @BeforeEach
    void setUp() {
        api = new ApiBrasil();
    }

    @AfterEach
    void tearDown() {
        if (api != null) {
            api.close();
        }
    }

    @Test
    @DisplayName("catálogo público responde")
    void documentations() {
        Map<String, Object> response = api.catalog.documentations();
        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    @DisplayName("saldo da conta responde (exige APIBRASIL_BEARER_TOKEN)")
    @EnabledIfEnvironmentVariable(named = "APIBRASIL_BEARER_TOKEN", matches = ".+")
    void balance() {
        assertNotNull(api.account.balance());
    }

    @Test
    @DisplayName("consulta em homologação não cobra (exige token)")
    @EnabledIfEnvironmentVariable(named = "APIBRASIL_BEARER_TOKEN", matches = ".+")
    void consultaHomolog() {
        Map<String, Object> response = api.consulta.cpf(
                Json.of("cpf", "00000000000", "homolog", true));
        assertNotNull(response);
    }

    @Test
    @DisplayName("as variáveis de ambiente reconhecidas são as documentadas")
    void environmentVariables() {
        assertNotNull(Env.BEARER_TOKEN);
        assertNotNull(Env.DEVICE_TOKEN);
        assertNotNull(Env.SECRET_KEY);
        assertNotNull(Env.BASE_URL);
    }
}
