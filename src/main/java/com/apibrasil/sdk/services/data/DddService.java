package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.services.DeviceProxyService;

/**
 * DDD ({@code /ddd/{action}}). Device-based.
 *
 * <p>As actions são dinâmicas — use {@link #request(String, Object)}.
 */
public class DddService extends DeviceProxyService {

    public DddService(ApiHttpClient http) {
        super(http, "ddd");
    }
}
