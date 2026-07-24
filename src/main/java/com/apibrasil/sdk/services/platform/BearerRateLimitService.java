package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Rate limit por Bearer Token ({@code /bearer-rate-limit}). */
public class BearerRateLimitService extends BaseService {

    public BearerRateLimitService(ApiHttpClient http) {
        super(http);
    }

    /** Consulta o limite atual: {@code GET /bearer-rate-limit}. */
    public Map<String, Object> get() {
        return get("bearer-rate-limit");
    }

    /** Define o limite: {@code PUT /bearer-rate-limit}. */
    public Map<String, Object> set(Map<String, Object> body) {
        return put("bearer-rate-limit", body);
    }
}
