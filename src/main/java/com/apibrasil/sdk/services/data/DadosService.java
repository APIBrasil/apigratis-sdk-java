package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/**
 * Dados cadastrais device-based ({@code /dados/{action}}): CPF, CNPJ, lista de
 * sócios, CNAEs etc.
 */
public class DadosService extends DeviceProxyService {

    public DadosService(ApiHttpClient http) {
        super(http, "dados");
    }

    /** Consulta CPF: {@code POST /dados/cpf} body {@code {"cpf": "..."}}. */
    public Map<String, Object> cpf(Map<String, Object> body) {
        return request("cpf", body);
    }

    public Map<String, Object> cpf(Map<String, Object> body, RequestOptions options) {
        return request("cpf", body, options);
    }

    /** Consulta CNPJ: {@code POST /dados/cnpj} body {@code {"cnpj": "..."}}. */
    public Map<String, Object> cnpj(Map<String, Object> body) {
        return request("cnpj", body);
    }

    public Map<String, Object> cnpj(Map<String, Object> body, RequestOptions options) {
        return request("cnpj", body, options);
    }

    /** Consulta por query: {@code POST /dados/byquery}. */
    public Map<String, Object> byQuery(Map<String, Object> body) {
        return request("byquery", body);
    }

    /** Capital social: {@code POST /dados/capital-social}. */
    public Map<String, Object> capitalSocial(Map<String, Object> body) {
        return request("capital-social", body);
    }

    /** Lista CNAEs: {@code POST /dados/lista-cnaes}. */
    public Map<String, Object> listaCnaes() {
        return request("lista-cnaes");
    }

    public Map<String, Object> listaCnaes(Map<String, Object> body) {
        return request("lista-cnaes", body);
    }

    /** Lista sócios: {@code POST /dados/lista-socios}. */
    public Map<String, Object> listaSocios(Map<String, Object> body) {
        return request("lista-socios", body);
    }

    /** Consulta UF: {@code POST /dados/uf}. */
    public Map<String, Object> uf(Map<String, Object> body) {
        return request("uf", body);
    }

    /** Consulta CEP: {@code POST /dados/cep}. */
    public Map<String, Object> cep(Map<String, Object> body) {
        return request("cep", body);
    }

    /** Créditos de CPF: {@code GET /dados/cpf/credits}. */
    public Map<String, Object> cpfCredits() {
        return get("dados/cpf/credits");
    }

    /** Créditos de CNPJ: {@code GET /dados/cnpj/credits}. */
    public Map<String, Object> cnpjCredits() {
        return get("dados/cnpj/credits");
    }
}
