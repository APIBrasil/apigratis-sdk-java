package com.apibrasil.sdk.core;

import java.util.function.Consumer;

/**
 * Hooks de observabilidade — logging, métricas, tracing.
 *
 * <pre>{@code
 * ApiBrasil api = ApiBrasil.builder()
 *     .hooks(Hooks.builder()
 *         .onRequest(info -> log.debug("{} {}", info.method(), info.url()))
 *         .onRetry(info -> log.warn("retry {} em {}", info.attempt(), info.delay()))
 *         .build())
 *     .build();
 * }</pre>
 */
public final class Hooks {

    /** Instância sem nenhum hook registrado. */
    public static final Hooks NONE = new Hooks(null, null, null);

    private final Consumer<RequestHookInfo> onRequest;
    private final Consumer<ResponseHookInfo> onResponse;
    private final Consumer<RetryHookInfo> onRetry;

    private Hooks(Consumer<RequestHookInfo> onRequest,
                  Consumer<ResponseHookInfo> onResponse,
                  Consumer<RetryHookInfo> onRetry) {
        this.onRequest = onRequest;
        this.onResponse = onResponse;
        this.onRetry = onRetry;
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Dispara o hook de requisição, se registrado. */
    public void fireRequest(RequestHookInfo info) {
        if (onRequest != null) {
            onRequest.accept(info);
        }
    }

    /** Dispara o hook de resposta, se registrado. */
    public void fireResponse(ResponseHookInfo info) {
        if (onResponse != null) {
            onResponse.accept(info);
        }
    }

    /** Dispara o hook de retry, se registrado. */
    public void fireRetry(RetryHookInfo info) {
        if (onRetry != null) {
            onRetry.accept(info);
        }
    }

    /** Builder de {@link Hooks}. */
    public static final class Builder {
        private Consumer<RequestHookInfo> onRequest;
        private Consumer<ResponseHookInfo> onResponse;
        private Consumer<RetryHookInfo> onRetry;

        public Builder onRequest(Consumer<RequestHookInfo> onRequest) {
            this.onRequest = onRequest;
            return this;
        }

        public Builder onResponse(Consumer<ResponseHookInfo> onResponse) {
            this.onResponse = onResponse;
            return this;
        }

        public Builder onRetry(Consumer<RetryHookInfo> onRetry) {
            this.onRetry = onRetry;
            return this;
        }

        public Hooks build() {
            return new Hooks(onRequest, onResponse, onRetry);
        }
    }
}
