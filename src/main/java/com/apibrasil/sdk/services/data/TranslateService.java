package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Tradução ({@code /translate/{action}}). Device-based. */
public class TranslateService extends DeviceProxyService {

    public TranslateService(ApiHttpClient http) {
        super(http, "translate");
    }

    /** Identifica o idioma: {@code POST /translate/identify}. */
    public Map<String, Object> identify(Map<String, Object> body) {
        return request("identify", body);
    }

    public Map<String, Object> identify(Map<String, Object> body, RequestOptions options) {
        return request("identify", body, options);
    }

    /** Lista os modelos: {@code POST /translate/models}. */
    public Map<String, Object> models() {
        return request("models");
    }

    public Map<String, Object> models(Map<String, Object> body) {
        return request("models", body);
    }
}
