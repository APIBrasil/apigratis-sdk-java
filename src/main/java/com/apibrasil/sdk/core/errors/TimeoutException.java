package com.apibrasil.sdk.core.errors;

/**
 * Timeout — a requisição pode ter sido processada; a SDK não faz retry
 * automático para não duplicar cobranças/envios.
 */
public class TimeoutException extends NetworkException {

    private static final long serialVersionUID = 1L;

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
