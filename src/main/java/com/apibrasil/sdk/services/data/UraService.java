package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** URA reversa / ligações ({@code /ura/call/*}). */
public class UraService extends BaseService {

    public UraService(ApiHttpClient http) {
        super(http);
    }

    /** Disca: {@code POST /ura/call/dialler}. */
    public Map<String, Object> dialler(Map<String, Object> body) {
        return post("ura/call/dialler", body);
    }

    public Map<String, Object> dialler(Map<String, Object> body, RequestOptions options) {
        return post("ura/call/dialler", body, options);
    }

    /** Status da ligação: {@code POST /ura/call/status?callId=...}. */
    public Map<String, Object> status(String callId) {
        return post("ura/call/status", null, RequestOptions.query("callId", callId));
    }
}
