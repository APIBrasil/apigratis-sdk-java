package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** OCR / Google Vision ({@code /recognize/{action}}). Device-based. */
public class RecognizeService extends DeviceProxyService {

    public RecognizeService(ApiHttpClient http) {
        super(http, "recognize");
    }

    /** Reconhece a partir de base64: {@code POST /recognize/base64}. */
    public Map<String, Object> base64(Map<String, Object> body) {
        return request("base64", body);
    }

    public Map<String, Object> base64(Map<String, Object> body, RequestOptions options) {
        return request("base64", body, options);
    }

    /** Reconhece a partir de uma URI: {@code POST /recognize/uri}. */
    public Map<String, Object> uri(Map<String, Object> body) {
        return request("uri", body);
    }
}
