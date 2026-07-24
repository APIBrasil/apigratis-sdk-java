package com.apibrasil.sdk.core;

import java.util.Map;

/**
 * Dados da requisição entregues ao hook {@code onRequest}.
 *
 * @param method  verbo HTTP
 * @param url     URL absoluta, já com query string
 * @param headers headers enviados
 * @param body    corpo ainda não serializado ({@code null} quando não há)
 * @param attempt tentativa atual (0 = primeira)
 */
public record RequestHookInfo(
        HttpMethod method,
        String url,
        Map<String, String> headers,
        Object body,
        int attempt) {
}
