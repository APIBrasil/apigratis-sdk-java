package com.apibrasil.sdk.core.errors;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

/**
 * Mapeia um status HTTP + corpo de erro para a subclasse adequada de
 * {@link ApiBrasilException}.
 */
public final class ErrorFactory {

    private ErrorFactory() {
    }

    /** Cria o erro correspondente ao status HTTP recebido. */
    public static ApiBrasilException create(int status, Object data, Map<String, String> headers) {
        return create(status, data, headers, null);
    }

    /** Cria o erro correspondente ao status HTTP recebido, preservando a causa. */
    public static ApiBrasilException create(int status, Object data, Map<String, String> headers, Throwable cause) {
        String message = extractMessage(status, data);
        String code = extractCode(data);

        if (status == 400 || status == 422) {
            return new ValidationException(message, status, code, data, cause);
        }
        if (status == 401) {
            return new AuthenticationException(message, status, code, data, cause);
        }
        if (status == 402) {
            return new InsufficientBalanceException(message, status, code, data, cause);
        }
        if (status == 403) {
            return new PermissionException(message, status, code, data, cause);
        }
        if (status == 404 || status == 410) {
            return new NotFoundException(message, status, code, data, cause);
        }
        if (status == 429) {
            return new RateLimitException(message, status, code, data, cause, parseRetryAfter(headers));
        }
        if (status >= 500) {
            return new ServerException(message, status, code, data, cause);
        }
        return new ApiBrasilException(message, status, code, data, cause);
    }

    private static String extractMessage(int status, Object data) {
        if (data instanceof Map<?, ?> map) {
            Object message = map.get("message");
            if (message instanceof String text && !text.isBlank()) {
                return text;
            }
            Object error = map.get("error");
            if (error instanceof String text && !text.isBlank()) {
                return text;
            }
        }
        return "A API respondeu com HTTP " + status + ".";
    }

    private static String extractCode(Object data) {
        if (data instanceof Map<?, ?> map) {
            Object code = map.get("code");
            if (code instanceof String text && !text.isBlank()) {
                return text;
            }
        }
        return null;
    }

    /** Lê o header {@code Retry-After} (segundos ou data HTTP). */
    public static Duration parseRetryAfter(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return null;
        }

        String raw = null;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getKey() != null && "retry-after".equals(entry.getKey().toLowerCase(Locale.ROOT))) {
                raw = entry.getValue();
                break;
            }
        }
        if (raw == null || raw.isBlank()) {
            return null;
        }

        String value = raw.trim();
        try {
            double seconds = Double.parseDouble(value);
            return Duration.ofMillis(Math.max(0, Math.round(seconds * 1000)));
        } catch (NumberFormatException ignored) {
            // Segue para o formato de data HTTP.
        }

        try {
            ZonedDateTime at = ZonedDateTime.parse(value, DateTimeFormatter.RFC_1123_DATE_TIME);
            Duration delta = Duration.between(Instant.now(), at.toInstant());
            return delta.isNegative() ? Duration.ZERO : delta;
        } catch (Exception ignored) {
            return null;
        }
    }
}
