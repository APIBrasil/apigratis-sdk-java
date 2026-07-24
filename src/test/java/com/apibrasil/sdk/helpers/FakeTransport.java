package com.apibrasil.sdk.helpers;

import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.transport.Transport;
import com.apibrasil.sdk.core.transport.TransportRequest;
import com.apibrasil.sdk.core.transport.TransportResponse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * Transporte fake para testes: grava todas as requisições e responde com uma
 * fila programável (ou um fallback 200).
 */
public final class FakeTransport implements Transport {

    private final List<TransportRequest> calls = new ArrayList<>();
    private final Deque<Object> queue = new ArrayDeque<>();

    private TransportResponse fallback = ok();
    private boolean closed;

    /** Enfileira respostas (ou erros a lançar), consumidas em ordem. */
    public FakeTransport respondWith(Object... responses) {
        for (Object response : responses) {
            queue.addLast(response);
        }
        return this;
    }

    /** Define a resposta padrão quando a fila está vazia. */
    public FakeTransport setFallback(TransportResponse response) {
        this.fallback = response;
        return this;
    }

    @Override
    public TransportResponse send(TransportRequest request) {
        calls.add(request);
        Object next = queue.pollFirst();
        if (next == null) {
            next = fallback;
        }
        if (next instanceof RuntimeException error) {
            throw error;
        }
        return (TransportResponse) next;
    }

    @Override
    public void close() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    /** Todas as requisições recebidas, em ordem. */
    public List<TransportRequest> calls() {
        return calls;
    }

    public int count() {
        return calls.size();
    }

    /** Última requisição recebida. */
    public TransportRequest last() {
        if (calls.isEmpty()) {
            throw new IllegalStateException("Nenhuma requisição foi feita.");
        }
        return calls.get(calls.size() - 1);
    }

    /** URL da última requisição. */
    public String lastUrl() {
        return last().url();
    }

    /** Verbo da última requisição. */
    public String lastMethod() {
        return last().method().value();
    }

    /** Headers da última requisição. */
    public Map<String, String> lastHeaders() {
        return last().headers();
    }

    /** Body JSON decodificado da última requisição ({@code null} se não houve). */
    public Map<String, Object> lastBody() {
        String body = last().body();
        return body == null ? null : Json.asMap(Json.decode(body));
    }

    /** Body cru da última requisição. */
    public String lastRawBody() {
        return last().body();
    }

    /** Resposta 200 com corpo JSON. */
    public static TransportResponse ok() {
        return ok(Map.of("ok", true));
    }

    /** Resposta 200 com o corpo informado. */
    public static TransportResponse ok(Object data) {
        return new TransportResponse(200, Map.of(), data);
    }

    /** Resposta de erro HTTP. */
    public static TransportResponse httpError(int status, Object data) {
        return new TransportResponse(status, Map.of(), data);
    }

    /** Resposta de erro HTTP com headers (ex: {@code Retry-After}). */
    public static TransportResponse httpError(int status, Object data, Map<String, String> headers) {
        return new TransportResponse(status, headers, data);
    }
}
