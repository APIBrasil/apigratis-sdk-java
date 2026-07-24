package com.apibrasil.sdk.core;

import java.util.Locale;

/** Métodos HTTP usados pelo gateway. */
public enum HttpMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE;

    /** Verbo HTTP como enviado na requisição (ex: {@code POST}). */
    public String value() {
        return name();
    }

    /**
     * Converte um verbo textual (case-insensitive) no enum correspondente.
     *
     * @throws IllegalArgumentException se o verbo não for suportado
     */
    public static HttpMethod of(String method) {
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("Método HTTP não informado.");
        }
        return HttpMethod.valueOf(method.trim().toUpperCase(Locale.ROOT));
    }
}
