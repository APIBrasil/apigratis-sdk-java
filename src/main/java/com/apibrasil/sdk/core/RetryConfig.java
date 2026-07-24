package com.apibrasil.sdk.core;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Política de retry do cliente. Por padrão a SDK tenta novamente apenas em
 * HTTP 429 (rate limit) e em falhas de conexão — nunca em timeouts ou erros
 * de negócio, para não duplicar cobranças/envios.
 */
public final class RetryConfig {

    /** Política padrão: 2 novas tentativas, backoff de 300ms a 5s, apenas em HTTP 429. */
    public static final RetryConfig DEFAULT = new RetryConfig(
            2, Duration.ofMillis(300), Duration.ofSeconds(5), List.of(429));

    /** Desliga o retry — passe em {@link ApiBrasilConfig.Builder#retry(RetryConfig)}. */
    public static final RetryConfig DISABLED = new RetryConfig(
            0, Duration.ofMillis(300), Duration.ofSeconds(5), List.of());

    private final int retries;
    private final Duration minDelay;
    private final Duration maxDelay;
    private final List<Integer> retryOnStatuses;

    private RetryConfig(int retries, Duration minDelay, Duration maxDelay, List<Integer> retryOnStatuses) {
        this.retries = Math.max(0, retries);
        this.minDelay = Objects.requireNonNull(minDelay, "minDelay");
        this.maxDelay = Objects.requireNonNull(maxDelay, "maxDelay");
        this.retryOnStatuses = List.copyOf(retryOnStatuses);
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Número de novas tentativas além da original. Padrão: 2. */
    public int retries() {
        return retries;
    }

    /** Atraso base do backoff exponencial. Padrão: 300ms. */
    public Duration minDelay() {
        return minDelay;
    }

    /** Teto do atraso entre tentativas. Padrão: 5s. */
    public Duration maxDelay() {
        return maxDelay;
    }

    /** Status HTTP que disparam retry. Padrão: {@code [429]}. */
    public List<Integer> retryOnStatuses() {
        return retryOnStatuses;
    }

    /** Builder de {@link RetryConfig}, partindo dos valores padrão. */
    public static final class Builder {
        private int retries = DEFAULT.retries;
        private Duration minDelay = DEFAULT.minDelay;
        private Duration maxDelay = DEFAULT.maxDelay;
        private List<Integer> retryOnStatuses = DEFAULT.retryOnStatuses;

        public Builder retries(int retries) {
            this.retries = retries;
            return this;
        }

        public Builder minDelay(Duration minDelay) {
            this.minDelay = minDelay;
            return this;
        }

        public Builder maxDelay(Duration maxDelay) {
            this.maxDelay = maxDelay;
            return this;
        }

        public Builder retryOnStatuses(List<Integer> retryOnStatuses) {
            this.retryOnStatuses = retryOnStatuses;
            return this;
        }

        public Builder retryOnStatuses(int... statuses) {
            this.retryOnStatuses = java.util.Arrays.stream(statuses).boxed().toList();
            return this;
        }

        public RetryConfig build() {
            return new RetryConfig(retries, minDelay, maxDelay, retryOnStatuses);
        }
    }

    @Override
    public String toString() {
        return "RetryConfig{retries=" + retries
                + ", minDelay=" + minDelay
                + ", maxDelay=" + maxDelay
                + ", retryOnStatuses=" + retryOnStatuses + '}';
    }
}
