package com.apibrasil.sdk.core.errors;

/** Falha de rede — a requisição pode não ter chegado ao servidor. */
public class NetworkException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
