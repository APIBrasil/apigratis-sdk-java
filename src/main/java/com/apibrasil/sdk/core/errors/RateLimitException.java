package com.apibrasil.sdk.core.errors;

import java.time.Duration;

/** HTTP 429 — rate limit atingido. */
public class RateLimitException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    private final Duration retryAfter;

    public RateLimitException(String message, Integer status, String errorCode, Object response, Throwable cause,
                              Duration retryAfter) {
        super(message, status, errorCode, response, cause);
        this.retryAfter = retryAfter;
    }

    /** Espera sugerida pelo servidor (header {@code Retry-After}); {@code null} se ausente. */
    public Duration retryAfter() {
        return retryAfter;
    }
}
