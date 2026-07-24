package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Feriados ({@code /holidays/{action}}). Device-based. */
public class HolidaysService extends DeviceProxyService {

    public HolidaysService(ApiHttpClient http) {
        super(http, "holidays");
    }

    /** Consulta feriados: {@code POST /holidays/feriados}. */
    public Map<String, Object> feriados(Map<String, Object> body) {
        return request("feriados", body);
    }

    public Map<String, Object> feriados(Map<String, Object> body, RequestOptions options) {
        return request("feriados", body, options);
    }
}
