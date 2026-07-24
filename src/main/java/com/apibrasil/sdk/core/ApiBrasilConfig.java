package com.apibrasil.sdk.core;

import com.apibrasil.sdk.core.transport.Transport;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Configuração do cliente {@code ApiBrasil}.
 *
 * <p>Campos não informados são lidos das variáveis de ambiente
 * {@code APIBRASIL_BEARER_TOKEN}, {@code APIBRASIL_DEVICE_TOKEN},
 * {@code APIBRASIL_SECRET_KEY} e {@code APIBRASIL_BASE_URL}.
 */
public final class ApiBrasilConfig {

    private final String bearerToken;
    private final String deviceToken;
    private final String secretKey;
    private final String baseUrl;
    private final Duration timeout;
    private final Map<String, String> headers;
    private final Transport transport;
    private final RetryConfig retry;
    private final Hooks hooks;

    private ApiBrasilConfig(Builder builder) {
        this.bearerToken = builder.bearerToken;
        this.deviceToken = builder.deviceToken;
        this.secretKey = builder.secretKey;
        this.baseUrl = builder.baseUrl;
        this.timeout = builder.timeout;
        this.headers = builder.headers == null ? null : Map.copyOf(builder.headers);
        this.transport = builder.transport;
        this.retry = builder.retry;
        this.hooks = builder.hooks;
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Configuração vazia — tudo vem do ambiente e dos valores padrão. */
    public static ApiBrasilConfig empty() {
        return builder().build();
    }

    /** Token JWT obtido no login ({@code Authorization: Bearer <token>}). */
    public String bearerToken() {
        return bearerToken;
    }

    /** Token do dispositivo, exigido pelos serviços device-based. */
    public String deviceToken() {
        return deviceToken;
    }

    /** SecretKey da API (usada apenas na criação de devices). */
    public String secretKey() {
        return secretKey;
    }

    /** Base da API. Padrão: {@code https://gateway.apibrasil.io/api/v2}. */
    public String baseUrl() {
        return baseUrl;
    }

    /** Timeout das requisições. Padrão: 30s. */
    public Duration timeout() {
        return timeout;
    }

    /** Headers adicionais enviados em todas as requisições. */
    public Map<String, String> headers() {
        return headers;
    }

    /** Transporte HTTP customizado. Padrão: {@code JdkHttpTransport}. */
    public Transport transport() {
        return transport;
    }

    /** Política de retry. Use {@link RetryConfig#DISABLED} para desativar. */
    public RetryConfig retry() {
        return retry;
    }

    /** Hooks de observabilidade. */
    public Hooks hooks() {
        return hooks;
    }

    /** Devolve um builder já preenchido com esta configuração. */
    public Builder toBuilder() {
        return new Builder()
                .bearerToken(bearerToken)
                .deviceToken(deviceToken)
                .secretKey(secretKey)
                .baseUrl(baseUrl)
                .timeout(timeout)
                .headers(headers)
                .transport(transport)
                .retry(retry)
                .hooks(hooks);
    }

    /**
     * Sobrepõe esta configuração com {@code other} — os campos definidos em
     * {@code other} têm prioridade. É como a configuração explícita vence a
     * lida do ambiente.
     */
    public ApiBrasilConfig merge(ApiBrasilConfig other) {
        if (other == null) {
            return this;
        }

        Map<String, String> mergedHeaders = null;
        if (headers != null || other.headers != null) {
            mergedHeaders = new LinkedHashMap<>();
            if (headers != null) {
                mergedHeaders.putAll(headers);
            }
            if (other.headers != null) {
                mergedHeaders.putAll(other.headers);
            }
        }

        return new Builder()
                .bearerToken(other.bearerToken != null ? other.bearerToken : bearerToken)
                .deviceToken(other.deviceToken != null ? other.deviceToken : deviceToken)
                .secretKey(other.secretKey != null ? other.secretKey : secretKey)
                .baseUrl(other.baseUrl != null ? other.baseUrl : baseUrl)
                .timeout(other.timeout != null ? other.timeout : timeout)
                .headers(mergedHeaders)
                .transport(other.transport != null ? other.transport : transport)
                .retry(other.retry != null ? other.retry : retry)
                .hooks(other.hooks != null ? other.hooks : hooks)
                .build();
    }

    /** Builder de {@link ApiBrasilConfig}. */
    public static final class Builder {
        private String bearerToken;
        private String deviceToken;
        private String secretKey;
        private String baseUrl;
        private Duration timeout;
        private Map<String, String> headers;
        private Transport transport;
        private RetryConfig retry;
        private Hooks hooks;

        public Builder bearerToken(String bearerToken) {
            this.bearerToken = blankToNull(bearerToken);
            return this;
        }

        public Builder deviceToken(String deviceToken) {
            this.deviceToken = blankToNull(deviceToken);
            return this;
        }

        public Builder secretKey(String secretKey) {
            this.secretKey = blankToNull(secretKey);
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = blankToNull(baseUrl);
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        /** Timeout em milissegundos (paridade com as SDKs Node/PHP). */
        public Builder timeoutMillis(long timeoutMillis) {
            this.timeout = Duration.ofMillis(timeoutMillis);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        /** Adiciona um header enviado em todas as requisições. */
        public Builder header(String name, String value) {
            if (this.headers == null) {
                this.headers = new LinkedHashMap<>();
            } else {
                this.headers = new LinkedHashMap<>(this.headers);
            }
            this.headers.put(name, value);
            return this;
        }

        public Builder transport(Transport transport) {
            this.transport = transport;
            return this;
        }

        public Builder retry(RetryConfig retry) {
            this.retry = retry;
            return this;
        }

        public Builder hooks(Hooks hooks) {
            this.hooks = hooks;
            return this;
        }

        public ApiBrasilConfig build() {
            return new ApiBrasilConfig(this);
        }

        private static String blankToNull(String value) {
            return value == null || value.isBlank() ? null : value;
        }
    }
}
