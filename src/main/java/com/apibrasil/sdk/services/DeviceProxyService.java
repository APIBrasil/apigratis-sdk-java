package com.apibrasil.sdk.services;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;

import java.util.Map;

/**
 * Base dos serviços "device-based" do gateway ({@code /api/v2/{servico}/{action}}).
 * Exigem {@code Authorization: Bearer} + header {@code DeviceToken}.
 *
 * <p>Todos expõem {@link #request(String, Object)} como porta de saída
 * genérica — as actions são dinâmicas por provedor, consulte a documentação em
 * <a href="https://doc.apibrasil.io">doc.apibrasil.io</a>.
 *
 * <p>A resposta segue o envelope device-based
 * ({@code { error, message, response, ... }}) — veja
 * {@link com.apibrasil.sdk.core.DeviceResponse}.
 */
public class DeviceProxyService extends BaseService {

    private final String service;

    public DeviceProxyService(ApiHttpClient http, String service) {
        super(http);
        this.service = service == null ? "" : service.replaceAll("^/+|/+$", "");
    }

    /** Nome do serviço no gateway (primeiro segmento da rota). */
    public String service() {
        return service;
    }

    /** Executa uma action do serviço: {@code POST /{servico}/{action}}. */
    public Map<String, Object> request(String action) {
        return request(action, null, RequestOptions.NONE);
    }

    /** Executa uma action do serviço: {@code POST /{servico}/{action}}. */
    public Map<String, Object> request(String action, Object body) {
        return request(action, body, RequestOptions.NONE);
    }

    /** Executa uma action do serviço: {@code POST /{servico}/{action}}. */
    public Map<String, Object> request(String action, Object body, RequestOptions options) {
        return post(path(action), body, options);
    }

    /** Executa a action de forma assíncrona: {@code POST /{servico}/{action}/queue}. */
    public Map<String, Object> queue(String action, Object body) {
        return queue(action, body, RequestOptions.NONE);
    }

    /** Executa a action de forma assíncrona: {@code POST /{servico}/{action}/queue}. */
    public Map<String, Object> queue(String action, Object body, RequestOptions options) {
        return post(path(action) + "/queue", body, options);
    }

    /** Caminho completo de uma action deste serviço. */
    protected String path(String action) {
        String normalized = action == null ? "" : action.replaceAll("^/+|/+$", "");
        return normalized.isEmpty() ? service : service + "/" + normalized;
    }
}
