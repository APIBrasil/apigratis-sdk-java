package com.apibrasil.sdk.core.transport;

import java.util.Map;

/**
 * Resposta devolvida pela camada de {@link Transport}.
 *
 * @param status  status HTTP
 * @param headers headers da resposta, com as chaves em minúsculas
 * @param data    corpo já decodificado (JSON → Map/List; texto; bytes)
 */
public record TransportResponse(int status, Map<String, String> headers, Object data) {

    public TransportResponse {
        headers = headers == null ? Map.of() : Map.copyOf(headers);
    }

    /** Resposta de sucesso sem headers. */
    public static TransportResponse ok(Object data) {
        return new TransportResponse(200, Map.of(), data);
    }
}
