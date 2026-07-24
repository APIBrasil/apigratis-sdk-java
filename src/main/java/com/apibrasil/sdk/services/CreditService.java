package com.apibrasil.sdk.services;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;

import java.util.Map;

/**
 * Base das consultas por crédito ({@code /consulta/{servico}/credits}).
 * Debitam saldo da conta e exigem apenas {@code Authorization: Bearer} —
 * não precisam de DeviceToken.
 */
public class CreditService extends BaseService {

    public CreditService(ApiHttpClient http) {
        super(http);
    }

    /** Executa uma consulta: {@code POST /consulta/{servico}/credits}. */
    public Map<String, Object> request(String service, Object body) {
        return request(service, body, RequestOptions.NONE);
    }

    /** Executa uma consulta: {@code POST /consulta/{servico}/credits}. */
    public Map<String, Object> request(String service, Object body, RequestOptions options) {
        return post(path(service), body, options);
    }

    /** Verifica créditos disponíveis: {@code GET /consulta/{servico}/credits}. */
    public Map<String, Object> credits(String service) {
        return credits(service, RequestOptions.NONE);
    }

    /** Verifica créditos disponíveis: {@code GET /consulta/{servico}/credits}. */
    public Map<String, Object> credits(String service, RequestOptions options) {
        return get(path(service), options);
    }

    private static String path(String service) {
        String normalized = service == null ? "" : service.replaceAll("^/+|/+$", "");
        return "consulta/" + normalized + "/credits";
    }
}
