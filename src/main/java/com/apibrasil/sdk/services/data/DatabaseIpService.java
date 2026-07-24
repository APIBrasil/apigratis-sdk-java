package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** GeoIP ({@code POST /database/ip}). */
public class DatabaseIpService extends DeviceProxyService {

    public DatabaseIpService(ApiHttpClient http) {
        super(http, "database");
    }

    /** Consulta IP: {@code POST /database/ip} body {@code {"ip": "8.8.8.8"}}. */
    public Map<String, Object> ip(Map<String, Object> body) {
        return request("ip", body);
    }

    public Map<String, Object> ip(Map<String, Object> body, RequestOptions options) {
        return request("ip", body, options);
    }
}
