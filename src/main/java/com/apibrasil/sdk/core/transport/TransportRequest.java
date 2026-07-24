package com.apibrasil.sdk.core.transport;

import com.apibrasil.sdk.core.HttpMethod;
import com.apibrasil.sdk.core.ResponseType;

import java.time.Duration;
import java.util.Map;

/**
 * Requisição entregue à camada de {@link Transport}.
 *
 * @param method       verbo HTTP
 * @param url          URL absoluta, já com query string
 * @param headers      headers da requisição
 * @param body         corpo já serializado (JSON), ou {@code null}
 * @param timeout      timeout da requisição
 * @param responseType como decodificar o corpo da resposta
 */
public record TransportRequest(
        HttpMethod method,
        String url,
        Map<String, String> headers,
        String body,
        Duration timeout,
        ResponseType responseType) {

    public TransportRequest {
        headers = headers == null ? Map.of() : Map.copyOf(headers);
        responseType = responseType == null ? ResponseType.JSON : responseType;
    }
}
