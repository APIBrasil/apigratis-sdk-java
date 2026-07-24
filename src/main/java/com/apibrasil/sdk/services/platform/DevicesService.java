package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/**
 * Gestão de devices ({@code /devices/*}).
 *
 * <p>Devices são a credencial de consumo dos serviços device-based: crie um
 * device com a {@code SecretKey} da API desejada e use o {@code device_token}
 * retornado como header {@code DeviceToken}.
 *
 * <pre>{@code
 * Map<String, Object> device = api.devices.store(
 *     Json.of("device_name", "meu-bot", "type", "server"),
 *     RequestOptions.secretKey("SUA_SECRET_KEY"));
 *
 * api.setDeviceToken((String) Json.object(device, "device").get("device_token"));
 * }</pre>
 */
public class DevicesService extends BaseService {

    public DevicesService(ApiHttpClient http) {
        super(http);
    }

    /** Lista os devices do usuário: {@code GET /devices}. */
    public Map<String, Object> list() {
        return list(RequestOptions.NONE);
    }

    public Map<String, Object> list(RequestOptions options) {
        return get("devices", options);
    }

    /**
     * Cria um device: {@code POST /devices/store}.
     *
     * <p>A {@code SecretKey} da API (painel APIBrasil) vai no header — passe em
     * {@link RequestOptions#secretKey(String)} ou configure {@code secretKey}
     * no cliente.
     */
    public Map<String, Object> store(Map<String, Object> body) {
        return store(body, RequestOptions.NONE);
    }

    public Map<String, Object> store(Map<String, Object> body, RequestOptions options) {
        return post("devices/store", body, options);
    }

    /** Detalha um device: {@code GET /devices/show?search={device_token}}. */
    public Map<String, Object> show() {
        return show(null, RequestOptions.NONE);
    }

    public Map<String, Object> show(String deviceToken) {
        return show(deviceToken, RequestOptions.NONE);
    }

    public Map<String, Object> show(String deviceToken, RequestOptions options) {
        String search = deviceToken != null ? deviceToken : http.deviceToken();
        return get("devices/show", options.withQuery(Json.of("search", search)));
    }

    /** Atualiza um device: {@code POST /devices/update} (body com {@code device_token} + campos). */
    public Map<String, Object> update(Map<String, Object> body) {
        return post("devices/update", body);
    }

    /** Remove um device: {@code DELETE /devices/destroy}. */
    public Map<String, Object> destroy() {
        return destroy(null, RequestOptions.NONE);
    }

    public Map<String, Object> destroy(String deviceToken) {
        return destroy(deviceToken, RequestOptions.NONE);
    }

    public Map<String, Object> destroy(String deviceToken, RequestOptions options) {
        String search = deviceToken != null ? deviceToken : http.deviceToken();
        return delete("devices/destroy", Json.of("search", search), options);
    }

    /** Histórico de requisições do device: {@code POST /devices/requests}. */
    public Map<String, Object> requests() {
        return post("devices/requests", null);
    }

    public Map<String, Object> requests(Map<String, Object> body) {
        return post("devices/requests", body);
    }
}
