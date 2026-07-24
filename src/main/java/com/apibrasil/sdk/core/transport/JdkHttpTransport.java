package com.apibrasil.sdk.core.transport;

import com.apibrasil.sdk.core.errors.NetworkException;
import com.apibrasil.sdk.core.errors.TimeoutException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Transporte padrão da SDK, sobre o {@code java.net.http.HttpClient} do JDK —
 * sem dependências externas.
 */
public final class JdkHttpTransport implements Transport {

    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient client;

    /** Cria o transporte com um {@link HttpClient} padrão. */
    public JdkHttpTransport() {
        this(HttpClient.newBuilder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build());
    }

    /** Cria o transporte sobre um {@link HttpClient} já configurado (proxy, SSL, executor). */
    public JdkHttpTransport(HttpClient client) {
        this.client = client;
    }

    /** O {@link HttpClient} em uso. */
    public HttpClient client() {
        return client;
    }

    @Override
    public TransportResponse send(TransportRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(request.url()));

        request.headers().forEach((name, value) -> {
            if (name != null && value != null && !isRestricted(name)) {
                builder.header(name, value);
            }
        });

        HttpRequest.BodyPublisher publisher = request.body() == null
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(request.body(), StandardCharsets.UTF_8);
        builder.method(request.method().value(), publisher);

        Duration timeout = request.timeout();
        if (timeout != null && !timeout.isZero() && !timeout.isNegative()) {
            builder.timeout(timeout);
        }

        HttpResponse<byte[]> response;
        try {
            response = client.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());
        } catch (HttpTimeoutException error) {
            throw new TimeoutException(timeoutMessage(request), error);
        } catch (IOException error) {
            throw new NetworkException(networkMessage(request, error), error);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new NetworkException(networkMessage(request, error), error);
        }

        return new TransportResponse(
                response.statusCode(),
                normalizeHeaders(response.headers().map()),
                BodyDecoder.decode(response.body(), request.responseType()));
    }

    private static String timeoutMessage(TransportRequest request) {
        long millis = request.timeout() == null ? 0 : request.timeout().toMillis();
        return "Tempo limite de " + millis + "ms excedido em "
                + request.method().value() + " " + request.url() + ".";
    }

    private static String networkMessage(TransportRequest request, Throwable error) {
        return "Falha de rede em " + request.method().value() + " " + request.url() + ": " + error;
    }

    /** Headers que o JDK não permite definir manualmente. */
    private static boolean isRestricted(String name) {
        String header = name.toLowerCase(java.util.Locale.ROOT);
        return header.equals("connection")
                || header.equals("content-length")
                || header.equals("expect")
                || header.equals("host")
                || header.equals("upgrade");
    }

    private static Map<String, String> normalizeHeaders(Map<String, List<String>> headers) {
        Map<String, String> normalized = new LinkedHashMap<>();
        headers.forEach((name, values) -> {
            if (name != null && values != null && !values.isEmpty()) {
                normalized.put(name.toLowerCase(java.util.Locale.ROOT), String.join(", ", values));
            }
        });
        return normalized;
    }
}
