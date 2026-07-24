package com.apibrasil.sdk.core.errors;

/** HTTP 404/410 — recurso não encontrado ou desativado. */
public class NotFoundException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
