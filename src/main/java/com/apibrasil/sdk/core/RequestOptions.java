package com.apibrasil.sdk.core;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/** Opções por requisição — sobrescrevem a configuração do cliente. */
public final class RequestOptions {

    /** Nenhuma opção — usa tudo do cliente. */
    public static final RequestOptions NONE = builder().build();

    private final Map<String, Object> query;
    private final Map<String, String> headers;
    private final String bearerToken;
    private final String deviceToken;
    private final String secretKey;
    private final Duration timeout;
    private final ResponseType responseType;

    private RequestOptions(Builder builder) {
        this.query = builder.query == null ? null : new LinkedHashMap<>(builder.query);
        this.headers = builder.headers == null ? null : new LinkedHashMap<>(builder.headers);
        this.bearerToken = builder.bearerToken;
        this.deviceToken = builder.deviceToken;
        this.secretKey = builder.secretKey;
        this.timeout = builder.timeout;
        this.responseType = builder.responseType;
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Atalho para uma requisição com query string. */
    public static RequestOptions query(Object... keyValues) {
        return builder().query(Json.of(keyValues)).build();
    }

    /** Atalho para uma requisição com a SecretKey (criação de devices). */
    public static RequestOptions secretKey(String secretKey) {
        return builder().secretKey(secretKey).build();
    }

    /** Atalho para uma requisição em outro device. */
    public static RequestOptions deviceToken(String deviceToken) {
        return builder().deviceToken(deviceToken).build();
    }

    /** Query string da requisição. Valores {@code null} são ignorados. */
    public Map<String, Object> queryParams() {
        return query;
    }

    /** Headers extras desta requisição. */
    public Map<String, String> headers() {
        return headers;
    }

    public String bearerToken() {
        return bearerToken;
    }

    public String deviceToken() {
        return deviceToken;
    }

    public String secretKey() {
        return secretKey;
    }

    public Duration timeout() {
        return timeout;
    }

    /** Como decodificar a resposta. Padrão: {@link ResponseType#JSON}. */
    public ResponseType responseType() {
        return responseType;
    }

    /** Devolve um builder já preenchido com estas opções. */
    public Builder toBuilder() {
        return new Builder()
                .query(query)
                .headers(headers)
                .bearerToken(bearerToken)
                .deviceToken(deviceToken)
                .secretKey(this.secretKey)
                .timeout(timeout)
                .responseType(responseType);
    }

    /** Devolve uma cópia com outro {@link ResponseType}. */
    public RequestOptions withResponseType(ResponseType responseType) {
        return toBuilder().responseType(responseType).build();
    }

    /** Mescla parâmetros de query mantendo os já definidos nesta instância. */
    public RequestOptions withQuery(Map<String, Object> extra) {
        if (extra == null || extra.isEmpty()) {
            return this;
        }
        Map<String, Object> merged = new LinkedHashMap<>(extra);
        if (query != null) {
            merged.putAll(query);
        }
        return toBuilder().query(merged).build();
    }

    /** Builder de {@link RequestOptions}. */
    public static final class Builder {
        private Map<String, Object> query;
        private Map<String, String> headers;
        private String bearerToken;
        private String deviceToken;
        private String secretKey;
        private Duration timeout;
        private ResponseType responseType;

        public Builder query(Map<String, Object> query) {
            this.query = query;
            return this;
        }

        /** Adiciona um parâmetro de query. */
        public Builder query(String name, Object value) {
            if (this.query == null) {
                this.query = new LinkedHashMap<>();
            }
            this.query.put(name, value);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        /** Adiciona um header a esta requisição. */
        public Builder header(String name, String value) {
            if (this.headers == null) {
                this.headers = new LinkedHashMap<>();
            }
            this.headers.put(name, value);
            return this;
        }

        public Builder bearerToken(String bearerToken) {
            this.bearerToken = bearerToken;
            return this;
        }

        public Builder deviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
            return this;
        }

        public Builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder timeoutMillis(long timeoutMillis) {
            this.timeout = Duration.ofMillis(timeoutMillis);
            return this;
        }

        public Builder responseType(ResponseType responseType) {
            this.responseType = responseType;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}
