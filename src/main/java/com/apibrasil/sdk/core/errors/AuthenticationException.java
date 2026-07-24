package com.apibrasil.sdk.core.errors;

/** HTTP 401 — Bearer Token ausente, inválido ou expirado. */
public class AuthenticationException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
