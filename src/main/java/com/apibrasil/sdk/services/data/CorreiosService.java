package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Correios ({@code /correios/{action}}). */
public class CorreiosService extends DeviceProxyService {

    public CorreiosService(ApiHttpClient http) {
        super(http, "correios");
    }

    /** Rastreio: {@code POST /correios/rastreio} body {@code {"code": "..."}}. */
    public Map<String, Object> rastreio(Map<String, Object> body) {
        return request("rastreio", body);
    }

    public Map<String, Object> rastreio(Map<String, Object> body, RequestOptions options) {
        return request("rastreio", body, options);
    }
}
