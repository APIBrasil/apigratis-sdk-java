package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/** Veículos por placa ({@code /vehicles/{action}}). */
public class VehiclesService extends DeviceProxyService {

    public VehiclesService(ApiHttpClient http) {
        super(http, "vehicles");
    }

    /** Dados do veículo: {@code POST /vehicles/dados} body {@code {"placa": "ABC1234"}}. */
    public Map<String, Object> dados(Map<String, Object> body) {
        return request("dados", body);
    }

    public Map<String, Object> dados(Map<String, Object> body, RequestOptions options) {
        return request("dados", body, options);
    }

    /** FIPE do veículo: {@code POST /vehicles/fipe} body {@code {"placa": "ABC1234"}}. */
    public Map<String, Object> fipe(Map<String, Object> body) {
        return request("fipe", body);
    }

    /** Base nacional: {@code POST /vehicles/base/000/dados}. */
    public Map<String, Object> baseDados(Map<String, Object> body) {
        return request("base/000/dados", body);
    }
}
