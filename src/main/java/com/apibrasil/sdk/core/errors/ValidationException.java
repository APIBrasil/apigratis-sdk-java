package com.apibrasil.sdk.core.errors;

/** HTTP 400/422 — payload inválido. */
public class ValidationException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
