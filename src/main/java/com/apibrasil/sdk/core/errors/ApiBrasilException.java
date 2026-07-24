package com.apibrasil.sdk.core.errors;

import java.util.Map;

/**
 * Erro base lançado pelo cliente {@code ApiBrasil}. Subclasses específicas
 * permitem tratar cada categoria com {@code catch} ou {@code instanceof}:
 *
 * <ul>
 *   <li>{@link ValidationException} (400/422), {@link AuthenticationException} (401),
 *       {@link InsufficientBalanceException} (402), {@link PermissionException} (403),
 *       {@link NotFoundException} (404/410), {@link RateLimitException} (429),
 *       {@link ServerException} (5xx)</li>
 *   <li>{@link NetworkException} / {@link TimeoutException} para falhas antes da resposta.</li>
 * </ul>
 *
 * <p>Todas são {@link RuntimeException} — não há {@code throws} obrigatório
 * nas assinaturas dos serviços.
 */
public class ApiBrasilException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Integer status;
    private final String errorCode;
    private final transient Object response;

    public ApiBrasilException(String message) {
        this(message, null, null, null, null);
    }

    public ApiBrasilException(String message, Throwable cause) {
        this(message, null, null, null, cause);
    }

    public ApiBrasilException(String message, Integer status, String errorCode, Object response, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = errorCode;
        this.response = response;
    }

    /** Status HTTP retornado pela API (ex: 401, 402, 404); {@code null} em falhas de rede. */
    public Integer status() {
        return status;
    }

    /** Código de erro retornado pela API (ex: {@code NOT_FOUND}). */
    public String errorCode() {
        return errorCode;
    }

    /** Corpo completo da resposta de erro, quando existir. */
    public Object response() {
        return response;
    }

    /** Corpo da resposta de erro como objeto JSON ({@code null} se não for um objeto). */
    @SuppressWarnings("unchecked")
    public Map<String, Object> responseAsMap() {
        if (response instanceof Map<?, ?> map) {
            return (Map<String, Object>) map;
        }
        return null;
    }

    /** {@code true} quando a falha foi por saldo/créditos insuficientes (HTTP 402). */
    public boolean isInsufficientBalance() {
        return status != null && status == 402;
    }

    /** {@code true} quando a falha foi de autenticação (HTTP 401). */
    public boolean isUnauthorized() {
        return status != null && status == 401;
    }

    /** Converte qualquer erro em um {@link ApiBrasilException}. */
    public static ApiBrasilException from(Throwable error) {
        if (error instanceof ApiBrasilException apiError) {
            return apiError;
        }
        String message = error == null || error.getMessage() == null
                ? "Erro desconhecido."
                : error.getMessage();
        return new ApiBrasilException(message, error);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(getClass().getSimpleName())
                .append(": ")
                .append(getMessage());
        if (status != null) {
            buffer.append(" (HTTP ").append(status).append(')');
        }
        if (errorCode != null) {
            buffer.append(" [").append(errorCode).append(']');
        }
        return buffer.toString();
    }
}
