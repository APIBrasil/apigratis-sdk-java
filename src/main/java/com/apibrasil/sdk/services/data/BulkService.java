package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Execução em lote ({@code /bulk/*}). */
public class BulkService extends BaseService {

    public BulkService(ApiHttpClient http) {
        super(http);
    }

    /** Cria job em lote: {@code POST /bulk}. */
    public Map<String, Object> create(Map<String, Object> body) {
        return post("bulk", body);
    }

    public Map<String, Object> create(Map<String, Object> body, RequestOptions options) {
        return post("bulk", body, options);
    }

    /** Status do job: {@code GET /bulk/{id}}. */
    public Map<String, Object> status(String id) {
        return get("bulk/" + id);
    }

    /** Lista os jobs: {@code GET /bulk}. */
    public Map<String, Object> list() {
        return get("bulk");
    }
}
