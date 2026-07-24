package com.apibrasil.sdk.core.errors;

/** HTTP 403 — sem permissão (ex: API exige conta PJ). */
public class PermissionException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public PermissionException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
