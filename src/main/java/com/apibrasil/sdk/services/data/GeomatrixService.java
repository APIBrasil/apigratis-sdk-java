package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Matriz de distâncias ({@code /geomatrix/{action}}). Device-based. */
public class GeomatrixService extends DeviceProxyService {

    public GeomatrixService(ApiHttpClient http) {
        super(http, "geomatrix");
    }

    /** Calcula distância: {@code POST /geomatrix/distance}. */
    public Map<String, Object> distance(Map<String, Object> body) {
        return request("distance", body);
    }

    public Map<String, Object> distance(Map<String, Object> body, RequestOptions options) {
        return request("distance", body, options);
    }
}
