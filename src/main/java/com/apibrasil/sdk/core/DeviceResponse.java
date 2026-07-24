package com.apibrasil.sdk.core;

import java.util.Map;

/**
 * Envelope de resposta dos serviços device-based
 * ({@code { error, message, response, api_limit... }}).
 *
 * <p>É apenas uma leitura tipada por cima do {@code Map} devolvido pelos
 * serviços — use os getters ou o acesso por chave, como preferir:
 *
 * <pre>{@code
 * Map<String, Object> json = api.whatsapp.sendText(Json.of("number", n, "text", t));
 * DeviceResponse res = DeviceResponse.of(json);
 * if (!res.isError()) {
 *     System.out.println(res.response());
 * }
 * }</pre>
 */
public final class DeviceResponse {

    private final Map<String, Object> json;

    private DeviceResponse(Map<String, Object> json) {
        this.json = json == null ? Map.of() : json;
    }

    /** Embrulha a resposta crua do serviço. */
    public static DeviceResponse of(Map<String, Object> json) {
        return new DeviceResponse(json);
    }

    /** A resposta crua, como devolvida pelo serviço. */
    public Map<String, Object> json() {
        return json;
    }

    /** {@code true} quando o gateway sinalizou erro no envelope. */
    public boolean isError() {
        return Json.bool(json, "error");
    }

    /** Mensagem devolvida pelo gateway. */
    public String message() {
        return Json.string(json, "message");
    }

    /** Payload do provedor ({@code response}). */
    public Object response() {
        return json.get("response");
    }

    /** Payload do provedor como objeto JSON ({@code null} se não for um objeto). */
    public Map<String, Object> responseObject() {
        return Json.object(json, "response");
    }

    /** Limite de requisições do plano. */
    public Object apiLimit() {
        return json.get("api_limit");
    }

    /** Janela do limite (ex: {@code day}). */
    public Object apiLimitFor() {
        return json.get("api_limit_for");
    }

    /** Quanto do limite já foi consumido. */
    public Object apiLimitUsed() {
        return json.get("api_limit_used");
    }

    @Override
    public String toString() {
        return "DeviceResponse" + json;
    }
}
