package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.CreditService;

import java.util.Map;

/**
 * Consultas por crédito ({@code POST /consulta/{servico}/credits}).
 * Exigem apenas {@code Authorization: Bearer} — debitam saldo da conta.
 *
 * <pre>{@code
 * api.consulta.cpf(Json.of("cpf", "00000000000"));
 * api.consulta.cnpj(Json.of("cnpj", "00000000000000", "tipo", "lista-socios"));
 *
 * // qualquer produto do catálogo (veja Catalog.CONSULTA_TIPOS)
 * api.consulta.request("cpf", Json.of("cpf", "00000000000", "tipo", "serasa-score-pf"));
 *
 * // homologação (sandbox, sem cobrança)
 * api.consulta.cpf(Json.of("cpf", "00000000000", "homolog", true));
 * }</pre>
 */
public class ConsultaService extends CreditService {

    public ConsultaService(ApiHttpClient http) {
        super(http);
    }

    /** Consulta CPF: {@code POST /consulta/cpf/credits}. */
    public Map<String, Object> cpf(Map<String, Object> body) {
        return request("cpf", body);
    }

    public Map<String, Object> cpf(Map<String, Object> body, RequestOptions options) {
        return request("cpf", body, options);
    }

    /** Consulta CNPJ: {@code POST /consulta/cnpj/credits}. */
    public Map<String, Object> cnpj(Map<String, Object> body) {
        return request("cnpj", body);
    }

    public Map<String, Object> cnpj(Map<String, Object> body, RequestOptions options) {
        return request("cnpj", body, options);
    }

    /** Consulta CEP: {@code POST /consulta/cep/credits}. */
    public Map<String, Object> cep(Map<String, Object> body) {
        return request("cep", body);
    }

    /** Consulta veículos: {@code POST /consulta/vehicles/credits}. */
    public Map<String, Object> vehicles(Map<String, Object> body) {
        return request("vehicles", body);
    }

    /** Consulta veículos (PT-BR): {@code POST /consulta/veiculos/credits}. */
    public Map<String, Object> veiculos(Map<String, Object> body) {
        return request("veiculos", body);
    }

    /** Consulta FIPE: {@code POST /consulta/fipe/credits}. */
    public Map<String, Object> fipe(Map<String, Object> body) {
        return request("fipe", body);
    }

    /** Consulta GeoIP: {@code POST /consulta/geoip/credits}. */
    public Map<String, Object> geoip(Map<String, Object> body) {
        return request("geoip", body);
    }

    /** Consulta telefone: {@code POST /consulta/telefone/credits}. */
    public Map<String, Object> telefone(Map<String, Object> body) {
        return request("telefone", body);
    }

    /** Consulta DDD (Anatel): {@code POST /consulta/ddd-anatel/credits}. */
    public Map<String, Object> ddd(Map<String, Object> body) {
        return request("ddd-anatel", body);
    }

    /** Consulta rastreio: {@code POST /consulta/rastreio/credits}. */
    public Map<String, Object> rastreio(Map<String, Object> body) {
        return request("rastreio", body);
    }

    /** Consulta CRM: {@code POST /consulta/crm/credits}. */
    public Map<String, Object> crm(Map<String, Object> body) {
        return request("crm", body);
    }

    /** Consulta CRBM: {@code POST /consulta/crbm/credits}. */
    public Map<String, Object> crbm(Map<String, Object> body) {
        return request("crbm", body);
    }

    /** Consulta CRO: {@code POST /consulta/cro/credits}. */
    public Map<String, Object> cro(Map<String, Object> body) {
        return request("cro", body);
    }

    /** Consulta clima: {@code POST /consulta/weather-api/credits}. */
    public Map<String, Object> weather(Map<String, Object> body) {
        return request("weather-api", body);
    }

    /** Emissão de notas: {@code POST /consulta/emissao-notas/credits}. */
    public Map<String, Object> emissaoNotas(Map<String, Object> body) {
        return request("emissao-notas", body);
    }

    /** Frete ANTT: {@code POST /consulta/frete-antt/credits}. */
    public Map<String, Object> freteAntt(Map<String, Object> body) {
        return request("frete-antt", body);
    }

    /** API RNTRC: {@code POST /consulta/api-rntrc/credits}. */
    public Map<String, Object> apiRntrc(Map<String, Object> body) {
        return request("api-rntrc", body);
    }

    /** Consulta Quod: {@code POST /consulta/quod/credits}. */
    public Map<String, Object> quod(Map<String, Object> body) {
        return request("quod", body);
    }

    /** Consulta genérica em qualquer produto do catálogo. */
    public Map<String, Object> generic(String service, Map<String, Object> body) {
        return request(service, body);
    }

    public Map<String, Object> generic(String service, Map<String, Object> body, RequestOptions options) {
        return request(service, body, options);
    }
}
