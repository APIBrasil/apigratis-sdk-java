package com.apibrasil.sdk.core.transport;

/**
 * Camada de transporte HTTP da SDK. A implementação padrão usa o
 * {@code java.net.http.HttpClient} do JDK ({@link JdkHttpTransport}); injete a
 * sua para usar Apache HttpClient ({@link ApacheHttpTransport}), OkHttp,
 * proxies, mocks de teste etc.
 *
 * <p>Contrato: devolve a resposta para QUALQUER status HTTP; lança
 * {@code NetworkException}/{@code TimeoutException} apenas quando não houve
 * resposta.
 */
public interface Transport extends AutoCloseable {

    /** Executa a requisição e devolve a resposta bruta. */
    TransportResponse send(TransportRequest request);

    /** Libera os recursos do transporte (conexões abertas). */
    @Override
    default void close() {
        // Sem recursos a liberar por padrão.
    }
}
