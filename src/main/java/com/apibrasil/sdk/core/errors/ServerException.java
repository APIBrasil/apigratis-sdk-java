package com.apibrasil.sdk.core.errors;

/** HTTP 5xx — erro interno do gateway/provedor. */
public class ServerException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public ServerException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
