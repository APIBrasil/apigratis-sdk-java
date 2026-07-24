package com.apibrasil.sdk.services.data;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Loterias ({@code /loterias/{sorteio}/{concurso}}). */
public class LoteriasService extends BaseService {

    public LoteriasService(ApiHttpClient http) {
        super(http);
    }

    /** Resultado por sorteio e concurso: {@code POST /loterias/{sorteio}/{concurso}}. */
    public Map<String, Object> resultado(String sorteio, int concurso) {
        return post("loterias/" + sorteio + "/" + concurso, null);
    }

    public Map<String, Object> resultado(String sorteio, int concurso, Map<String, Object> body,
                                         RequestOptions options) {
        return post("loterias/" + sorteio + "/" + concurso, body, options);
    }

    /** Último resultado: {@code POST /loterias/{sorteio}/latest}. */
    public Map<String, Object> latest(String sorteio) {
        return post("loterias/" + sorteio + "/latest", null);
    }

    public Map<String, Object> latest(String sorteio, Map<String, Object> body, RequestOptions options) {
        return post("loterias/" + sorteio + "/latest", body, options);
    }
}
