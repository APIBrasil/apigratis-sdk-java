package com.apibrasil.sdk.core;

import java.time.Duration;

/**
 * Dados do retry entregues ao hook {@code onRetry}.
 *
 * @param method  verbo HTTP
 * @param url     URL absoluta
 * @param attempt número da próxima tentativa
 * @param delay   espera antes da próxima tentativa
 * @param reason  motivo do retry (ex: {@code HTTP 429})
 */
public record RetryHookInfo(
        HttpMethod method,
        String url,
        int attempt,
        Duration delay,
        String reason) {
}
