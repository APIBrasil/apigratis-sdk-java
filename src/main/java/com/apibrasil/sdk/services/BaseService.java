package com.apibrasil.sdk.services;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.HttpMethod;
import com.apibrasil.sdk.core.RequestOptions;

import java.util.Map;
import java.util.Objects;

/** Base de todos os serviços da SDK. */
public abstract class BaseService {

    /** Cliente HTTP compartilhado por todos os serviços do mesmo {@code ApiBrasil}. */
    protected final ApiHttpClient http;

    protected BaseService(ApiHttpClient http) {
        this.http = Objects.requireNonNull(http, "http");
    }

    /** O cliente HTTP em uso. */
    public ApiHttpClient http() {
        return http;
    }

    /** Monta a URL completa de um caminho. */
    public String buildUrl(String path) {
        return ApiHttpClient.joinUrl(http.baseUrl(), path);
    }

    protected Map<String, Object> get(String path) {
        return http.get(path, RequestOptions.NONE);
    }

    protected Map<String, Object> get(String path, RequestOptions options) {
        return http.get(path, options);
    }

    protected Map<String, Object> post(String path, Object body) {
        return http.post(path, body, RequestOptions.NONE);
    }

    protected Map<String, Object> post(String path, Object body, RequestOptions options) {
        return http.post(path, body, options);
    }

    protected Map<String, Object> put(String path, Object body) {
        return http.put(path, body, RequestOptions.NONE);
    }

    protected Map<String, Object> put(String path, Object body, RequestOptions options) {
        return http.put(path, body, options);
    }

    protected Map<String, Object> patch(String path, Object body) {
        return http.patch(path, body, RequestOptions.NONE);
    }

    protected Map<String, Object> patch(String path, Object body, RequestOptions options) {
        return http.patch(path, body, options);
    }

    protected Map<String, Object> delete(String path) {
        return http.delete(path, null, RequestOptions.NONE);
    }

    protected Map<String, Object> delete(String path, Object body, RequestOptions options) {
        return http.delete(path, body, options);
    }

    /** Baixa o corpo cru (PDF, imagens...). */
    protected byte[] download(String path, RequestOptions options) {
        return http.bytes(HttpMethod.GET, path, null, options);
    }
}
