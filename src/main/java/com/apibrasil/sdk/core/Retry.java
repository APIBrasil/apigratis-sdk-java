package com.apibrasil.sdk.core;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/** Cálculo do backoff e pausa entre tentativas. */
public final class Retry {

    private Retry() {
    }

    /** Resolve a política efetiva — {@code null} usa {@link RetryConfig#DEFAULT}. */
    public static RetryConfig resolve(RetryConfig config) {
        return config == null ? RetryConfig.DEFAULT : config;
    }

    /**
     * Backoff exponencial com jitter: {@code minDelay * 2^attempt}, limitado a
     * {@code maxDelay}.
     */
    public static Duration backoffDelay(int attempt, RetryConfig retry) {
        double exponential = retry.minDelay().toMillis() * Math.pow(2, attempt);
        double jitter = 0.5 + ThreadLocalRandom.current().nextDouble() * 0.5;
        long delayMs = Math.round(exponential * jitter);
        return delayMs >= retry.maxDelay().toMillis() ? retry.maxDelay() : Duration.ofMillis(delayMs);
    }

    /** Aguarda {@code delay}, preservando o status de interrupção da thread. */
    public static void sleep(Duration delay) {
        if (delay == null || delay.isNegative() || delay.isZero()) {
            return;
        }
        try {
            Thread.sleep(delay.toMillis());
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }
}
