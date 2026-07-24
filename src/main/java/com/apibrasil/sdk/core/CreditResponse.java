package com.apibrasil.sdk.core;

import java.util.Map;

/**
 * Envelope de resposta das consultas por crédito
 * ({@code { error, message, balance, tax, valor_consulta, data... }}).
 *
 * <pre>{@code
 * CreditResponse res = CreditResponse.of(api.consulta.cnpj(Json.of("cnpj", cnpj)));
 * System.out.println(res.balance() + " -> " + res.data());
 * }</pre>
 */
public final class CreditResponse {

    private final Map<String, Object> json;

    private CreditResponse(Map<String, Object> json) {
        this.json = json == null ? Map.of() : json;
    }

    /** Embrulha a resposta crua da consulta. */
    public static CreditResponse of(Map<String, Object> json) {
        return new CreditResponse(json);
    }

    /** A resposta crua, como devolvida pelo serviço. */
    public Map<String, Object> json() {
        return json;
    }

    /** {@code true} quando o gateway sinalizou erro no envelope. */
    public boolean isError() {
        return Json.bool(json, "error");
    }

    /** Mensagem devolvida pelo gateway. */
    public String message() {
        return Json.string(json, "message");
    }

    /** Saldo restante após a consulta. */
    public Object balance() {
        return json.get("balance");
    }

    /** Taxa aplicada. */
    public Object tax() {
        return json.get("tax");
    }

    /** Valor cobrado pela consulta. */
    public Object valorConsulta() {
        return json.get("valor_consulta");
    }

    /** {@code true} quando a resposta veio do modo homologação. */
    public boolean homolog() {
        return Json.bool(json, "homolog");
    }

    /** Dados da consulta. */
    public Object data() {
        return json.get("data");
    }

    /** Dados da consulta como objeto JSON ({@code null} se não for um objeto). */
    public Map<String, Object> dataObject() {
        return Json.object(json, "data");
    }

    @Override
    public String toString() {
        return "CreditResponse" + json;
    }
}
