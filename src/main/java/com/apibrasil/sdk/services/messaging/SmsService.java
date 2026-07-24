package com.apibrasil.sdk.services.messaging;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/**
 * SMS device-based ({@code POST /sms/{action}}) e por créditos
 * ({@code POST /sms/send/credits}).
 */
public class SmsService extends DeviceProxyService {

    public SmsService(ApiHttpClient http) {
        super(http, "sms");
    }

    /**
     * Envia SMS pelo device: {@code POST /sms/send}.
     * Campos: {@code number}, {@code message}, {@code operator},
     * {@code user_reply}, {@code webhook_url}.
     */
    public Map<String, Object> send(Map<String, Object> body) {
        return request("send", body);
    }

    public Map<String, Object> send(Map<String, Object> body, RequestOptions options) {
        return request("send", body, options);
    }

    /** Envia SMS debitando créditos da conta (sem DeviceToken): {@code POST /sms/send/credits}. */
    public Map<String, Object> sendWithCredits(Map<String, Object> body) {
        return sendWithCredits(body, RequestOptions.NONE);
    }

    public Map<String, Object> sendWithCredits(Map<String, Object> body, RequestOptions options) {
        return post("sms/send/credits", body, options);
    }

    /** Envia SMS pela fila (assíncrono): {@code POST /sms/send/queue}. */
    public Map<String, Object> sendQueue(Map<String, Object> body) {
        return request("send/queue", body);
    }
}
