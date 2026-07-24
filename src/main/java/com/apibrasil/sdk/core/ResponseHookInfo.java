package com.apibrasil.sdk.core;

import java.time.Duration;

/**
 * Dados da resposta entregues ao hook {@code onResponse}.
 *
 * @param method   verbo HTTP
 * @param url      URL absoluta
 * @param status   status HTTP da resposta
 * @param duration tempo total da tentativa
 * @param attempt  tentativa atual (0 = primeira)
 */
public record ResponseHookInfo(
        HttpMethod method,
        String url,
        int status,
        Duration duration,
        int attempt) {
}
