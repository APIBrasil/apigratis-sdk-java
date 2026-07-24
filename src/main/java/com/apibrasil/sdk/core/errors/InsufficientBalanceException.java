package com.apibrasil.sdk.core.errors;

/** HTTP 402 — saldo/créditos insuficientes. */
public class InsufficientBalanceException extends ApiBrasilException {

    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException(String message, Integer status, String errorCode, Object response,
                                        Throwable cause) {
        super(message, status, errorCode, response, cause);
    }
}
