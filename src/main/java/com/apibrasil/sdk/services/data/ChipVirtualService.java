package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Chip virtual ({@code /chip/virtual/{action}}). */
public class ChipVirtualService extends BaseService {

    public ChipVirtualService(ApiHttpClient http) {
        super(http);
    }

    /** Compra chip: {@code POST /chip/virtual/buy}. */
    public Map<String, Object> buy(Map<String, Object> body) {
        return post("chip/virtual/buy", body);
    }

    public Map<String, Object> buy(Map<String, Object> body, RequestOptions options) {
        return post("chip/virtual/buy", body, options);
    }

    /** Ativação: {@code POST /chip/virtual/activation}. */
    public Map<String, Object> activation(Map<String, Object> body) {
        return post("chip/virtual/activation", body);
    }

    /** Operadoras disponíveis: {@code GET /chip/virtual/operators}. */
    public Map<String, Object> operators() {
        return get("chip/virtual/operators");
    }

    /** Serviços disponíveis: {@code GET /chip/virtual/services}. */
    public Map<String, Object> services() {
        return get("chip/virtual/services");
    }
}
