package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Geolocalização ({@code /geolocation/{action}}). Device-based. */
public class GeolocationService extends DeviceProxyService {

    public GeolocationService(ApiHttpClient http) {
        super(http, "geolocation");
    }

    /** Geocode: {@code POST /geolocation/geocode}. */
    public Map<String, Object> geocode(Map<String, Object> body) {
        return request("geocode", body);
    }

    public Map<String, Object> geocode(Map<String, Object> body, RequestOptions options) {
        return request("geocode", body, options);
    }

    /** Forward geocoding: {@code POST /geolocation/forward-geocoding}. */
    public Map<String, Object> forwardGeocoding(Map<String, Object> body) {
        return request("forward-geocoding", body);
    }
}
