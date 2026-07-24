package com.apibrasil.sdk.core;

/**
 * Leitura da configuração a partir das variáveis de ambiente (com fallback
 * para as propriedades de sistema de mesmo nome, úteis em testes e em
 * {@code -D} na linha de comando).
 */
public final class Env {

    /** Bearer Token (JWT) obtido no login. */
    public static final String BEARER_TOKEN = "APIBRASIL_BEARER_TOKEN";

    /** Token do dispositivo, exigido pelos serviços device-based. */
    public static final String DEVICE_TOKEN = "APIBRASIL_DEVICE_TOKEN";

    /** SecretKey da API (usada na criação de devices). */
    public static final String SECRET_KEY = "APIBRASIL_SECRET_KEY";

    /** Base da API. */
    public static final String BASE_URL = "APIBRASIL_BASE_URL";

    private Env() {
    }

    /**
     * Lê a configuração do ambiente. Valores passados explicitamente ao
     * construir o cliente sempre têm prioridade.
     */
    public static ApiBrasilConfig config() {
        return ApiBrasilConfig.builder()
                .bearerToken(read(BEARER_TOKEN))
                .deviceToken(read(DEVICE_TOKEN))
                .secretKey(read(SECRET_KEY))
                .baseUrl(read(BASE_URL))
                .build();
    }

    /** Lê uma variável de ambiente ({@code null} quando ausente ou vazia). */
    public static String read(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            value = System.getProperty(name);
        }
        return value == null || value.isBlank() ? null : value;
    }
}
