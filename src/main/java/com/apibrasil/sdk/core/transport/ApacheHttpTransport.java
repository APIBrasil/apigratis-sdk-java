package com.apibrasil.sdk.core.transport;

import com.apibrasil.sdk.core.errors.NetworkException;
import com.apibrasil.sdk.core.errors.TimeoutException;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Transporte alternativo sobre o Apache HttpClient 5 — útil quando a
 * aplicação já usa pool de conexões, proxy ou SSL customizado do Apache.
 *
 * <pre>{@code
 * ApiBrasil api = ApiBrasil.builder()
 *     .transport(new ApacheHttpTransport())
 *     .build();
 * }</pre>
 */
public final class ApacheHttpTransport implements Transport {

    private final CloseableHttpClient client;
    private final boolean ownsClient;

    /** Cria o transporte com um {@link CloseableHttpClient} padrão. */
    public ApacheHttpTransport() {
        this(HttpClients.createDefault(), true);
    }

    /** Cria o transporte sobre um cliente já configurado (o fechamento fica com o chamador). */
    public ApacheHttpTransport(CloseableHttpClient client) {
        this(client, false);
    }

    private ApacheHttpTransport(CloseableHttpClient client, boolean ownsClient) {
        this.client = client;
        this.ownsClient = ownsClient;
    }

    @Override
    public TransportResponse send(TransportRequest request) {
        HttpUriRequestBase httpRequest =
                new HttpUriRequestBase(request.method().value(), URI.create(request.url()));

        request.headers().forEach((name, value) -> {
            if (name != null && value != null) {
                httpRequest.setHeader(name, value);
            }
        });

        if (request.body() != null) {
            httpRequest.setEntity(new StringEntity(
                    request.body(),
                    ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8)));
        }

        Duration timeout = request.timeout();
        if (timeout != null && !timeout.isZero() && !timeout.isNegative()) {
            httpRequest.setConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(Timeout.ofMilliseconds(timeout.toMillis()))
                    .setResponseTimeout(Timeout.ofMilliseconds(timeout.toMillis()))
                    .build());
        }

        try {
            return client.execute(httpRequest, response -> {
                HttpEntity entity = response.getEntity();
                byte[] bytes = entity == null ? new byte[0] : EntityUtils.toByteArray(entity);
                return new TransportResponse(
                        response.getCode(),
                        normalizeHeaders(response.getHeaders()),
                        BodyDecoder.decode(bytes, request.responseType()));
            });
        } catch (SocketTimeoutException error) {
            throw new TimeoutException(timeoutMessage(request), error);
        } catch (InterruptedIOException error) {
            Thread.currentThread().interrupt();
            throw new NetworkException(networkMessage(request, error), error);
        } catch (IOException error) {
            throw new NetworkException(networkMessage(request, error), error);
        }
    }

    @Override
    public void close() {
        if (!ownsClient) {
            return;
        }
        try {
            client.close();
        } catch (IOException ignored) {
            // Fechamento best-effort.
        }
    }

    private static String timeoutMessage(TransportRequest request) {
        long millis = request.timeout() == null ? 0 : request.timeout().toMillis();
        return "Tempo limite de " + millis + "ms excedido em "
                + request.method().value() + " " + request.url() + ".";
    }

    private static String networkMessage(TransportRequest request, Throwable error) {
        return "Falha de rede em " + request.method().value() + " " + request.url() + ": " + error;
    }

    private static Map<String, String> normalizeHeaders(Header[] headers) {
        Map<String, String> normalized = new LinkedHashMap<>();
        if (headers == null) {
            return normalized;
        }
        for (Header header : headers) {
            if (header == null || header.getName() == null || header.getValue() == null) {
                continue;
            }
            normalized.merge(
                    header.getName().toLowerCase(Locale.ROOT),
                    header.getValue(),
                    (first, second) -> first + ", " + second);
        }
        return normalized;
    }
}
