package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/**
 * CEP + geolocalização device-based ({@code /cep/{action}}).
 * Exige {@code DeviceToken}.
 */
public class CepService extends DeviceProxyService {

    public CepService(ApiHttpClient http) {
        super(http, "cep");
    }

    /** Consulta CEP: {@code POST /cep/cep} body {@code {"cep": "01001000"}}. */
    public Map<String, Object> cep(Map<String, Object> body) {
        return request("cep", body);
    }

    public Map<String, Object> cep(Map<String, Object> body, RequestOptions options) {
        return request("cep", body, options);
    }

    /** Consulta bairros: {@code POST /cep/bairros}. */
    public Map<String, Object> bairros(Map<String, Object> body) {
        return request("bairros", body);
    }

    /** Consulta cidades: {@code POST /cep/cidades}. */
    public Map<String, Object> cidades(Map<String, Object> body) {
        return request("cidades", body);
    }

    /** Consulta cidades por DDD: {@code POST /cep/cidadesPorDDD}. */
    public Map<String, Object> cidadesPorDdd(Map<String, Object> body) {
        return request("cidadesPorDDD", body);
    }

    /** Consulta estados: {@code POST /cep/estados}. */
    public Map<String, Object> estados() {
        return request("estados");
    }

    public Map<String, Object> estados(Map<String, Object> body) {
        return request("estados", body);
    }

    /** Calcula distância entre CEPs: {@code POST /cep/distancia/calcular}. */
    public Map<String, Object> calcularDistancia(Map<String, Object> body) {
        return request("distancia/calcular", body);
    }
}
