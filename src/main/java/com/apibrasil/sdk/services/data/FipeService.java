package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Tabela FIPE ({@code /fipe/{action}}). */
public class FipeService extends BaseService {

    private static final String SERVICE = "fipe";

    public FipeService(ApiHttpClient http) {
        super(http);
    }

    /** Executa qualquer action da FIPE: {@code POST /fipe/{action}}. */
    public Map<String, Object> request(String action, Object body) {
        return request(action, body, RequestOptions.NONE);
    }

    /** Executa qualquer action da FIPE: {@code POST /fipe/{action}}. */
    public Map<String, Object> request(String action, Object body, RequestOptions options) {
        return post(SERVICE + "/" + action, body, options);
    }

    /** Consultar marcas: {@code POST /fipe/ConsultarMarcas}. */
    public Map<String, Object> consultarMarcas(Map<String, Object> body) {
        return request("ConsultarMarcas", body);
    }

    /** Consultar modelos: {@code POST /fipe/ConsultarModelos}. */
    public Map<String, Object> consultarModelos(Map<String, Object> body) {
        return request("ConsultarModelos", body);
    }

    /** Consultar modelos através do ano: {@code POST /fipe/ConsultarModelosAtravesDoAno}. */
    public Map<String, Object> consultarModelosAtravesDoAno(Map<String, Object> body) {
        return request("ConsultarModelosAtravesDoAno", body);
    }

    /** Consultar tabela de referência: {@code POST /fipe/ConsultarTabelaDeReferencia}. */
    public Map<String, Object> consultarTabelaDeReferencia(Map<String, Object> body) {
        return request("ConsultarTabelaDeReferencia", body);
    }

    /** Consultar tabela de referência sem corpo. */
    public Map<String, Object> consultarTabelaDeReferencia() {
        return request("ConsultarTabelaDeReferencia", null);
    }

    /** Consultar ano modelo: {@code POST /fipe/ConsultarAnoModelo}. */
    public Map<String, Object> consultarAnoModelo(Map<String, Object> body) {
        return request("ConsultarAnoModelo", body);
    }

    /** Consultar valor com todos os parâmetros: {@code POST /fipe/ConsultarValorComTodosParametros}. */
    public Map<String, Object> consultarValorComTodosParametros(Map<String, Object> body) {
        return request("ConsultarValorComTodosParametros", body);
    }
}
