package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Clima ({@code /weather/{action}}). Device-based. */
public class WeatherService extends DeviceProxyService {

    public WeatherService(ApiHttpClient http) {
        super(http, "weather");
    }

    /** Por cidade: {@code POST /weather/city}. */
    public Map<String, Object> city(Map<String, Object> body) {
        return request("city", body);
    }

    public Map<String, Object> city(Map<String, Object> body, RequestOptions options) {
        return request("city", body, options);
    }

    /** Por coordenadas: {@code POST /weather/coordenates}. */
    public Map<String, Object> coordenates(Map<String, Object> body) {
        return request("coordenates", body);
    }
}
