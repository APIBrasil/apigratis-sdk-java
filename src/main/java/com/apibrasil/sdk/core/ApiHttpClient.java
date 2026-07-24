package com.apibrasil.sdk.core;

import com.apibrasil.sdk.core.errors.ApiBrasilException;
import com.apibrasil.sdk.core.errors.ErrorFactory;
import com.apibrasil.sdk.core.errors.NetworkException;
import com.apibrasil.sdk.core.errors.RateLimitException;
import com.apibrasil.sdk.core.errors.TimeoutException;
import com.apibrasil.sdk.core.transport.JdkHttpTransport;
import com.apibrasil.sdk.core.transport.Transport;
import com.apibrasil.sdk.core.transport.TransportRequest;
import com.apibrasil.sdk.core.transport.TransportResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Cliente HTTP interno da SDK. Injeta os headers de autenticação da
 * plataforma ({@code Authorization: Bearer}, {@code DeviceToken},
 * {@code SecretKey}), aplica retry com backoff, dispara hooks de
 * observabilidade e converte falhas em subclasses de
 * {@link ApiBrasilException}.
 */
public final class ApiHttpClient implements AutoCloseable {

    /** Base padrão da API. */
    public static final String DEFAULT_BASE_URL = "https://gateway.apibrasil.io/api/v2";

    /** Timeout padrão das requisições. */
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    /** User-Agent enviado pela SDK. */
    public static final String SDK_USER_AGENT = "APIBRASIL/SDK-JAVA";

    private final ApiBrasilConfig config;
    private final Transport transport;
    private final boolean ownsTransport;
    private final RetryConfig retry;
    private final Hooks hooks;

    private volatile String bearerToken;
    private volatile String deviceToken;

    /** Cria o cliente lendo tudo do ambiente. */
    public ApiHttpClient() {
        this(ApiBrasilConfig.empty());
    }

    /**
     * Cria o cliente. Campos não informados em {@code config} são lidos das
     * variáveis de ambiente reconhecidas por {@link Env}.
     */
    public ApiHttpClient(ApiBrasilConfig config) {
        this.config = Env.config().merge(config == null ? ApiBrasilConfig.empty() : config);
        this.bearerToken = this.config.bearerToken();
        this.deviceToken = this.config.deviceToken();
        this.ownsTransport = this.config.transport() == null;
        this.transport = this.ownsTransport ? new JdkHttpTransport() : this.config.transport();
        this.retry = Retry.resolve(this.config.retry());
        this.hooks = this.config.hooks() == null ? Hooks.NONE : this.config.hooks();
    }

    /** Base da API em uso. */
    public String baseUrl() {
        return config.baseUrl() == null ? DEFAULT_BASE_URL : config.baseUrl();
    }

    public String bearerToken() {
        return bearerToken;
    }

    public String deviceToken() {
        return deviceToken;
    }

    public String secretKey() {
        return config.secretKey();
    }

    /** Transporte HTTP em uso. */
    public Transport transport() {
        return transport;
    }

    /** Define/atualiza o Bearer Token — {@code null} remove a autenticação. */
    public void setBearerToken(String token) {
        this.bearerToken = token == null || token.isBlank() ? null : token;
    }

    /** Define/atualiza o DeviceToken — {@code null} remove o header. */
    public void setDeviceToken(String token) {
        this.deviceToken = token == null || token.isBlank() ? null : token;
    }

    /** Configuração atual, já resolvida com o ambiente e com os tokens em vigor. */
    public ApiBrasilConfig config() {
        return config.toBuilder()
                .bearerToken(bearerToken)
                .deviceToken(deviceToken)
                .transport(transport)
                .build();
    }

    /** Executa uma requisição e devolve o corpo já decodificado. */
    public Object request(HttpMethod method, String path, Object body, RequestOptions options) {
        RequestOptions opts = options == null ? RequestOptions.NONE : options;

        Map<String, String> headers = buildHeaders(opts);
        String url = joinUrl(baseUrl(), path) + buildQueryString(opts.queryParams());
        String serializedBody = body == null ? null : Json.encode(body);
        Duration timeout = firstNonNull(opts.timeout(), config.timeout(), DEFAULT_TIMEOUT);
        ResponseType responseType = opts.responseType() == null ? ResponseType.JSON : opts.responseType();
        int maxAttempts = 1 + retry.retries();

        int attempt = 0;
        while (true) {
            hooks.fireRequest(new RequestHookInfo(method, url, headers, body, attempt));

            long startedAt = System.nanoTime();
            TransportResponse response;
            try {
                response = transport.send(new TransportRequest(
                        method, url, headers, serializedBody, timeout, responseType));
            } catch (RuntimeException error) {
                boolean retryable = error instanceof NetworkException && !(error instanceof TimeoutException);
                if (retryable && attempt + 1 < maxAttempts) {
                    Duration delay = Retry.backoffDelay(attempt, retry);
                    attempt++;
                    hooks.fireRetry(new RetryHookInfo(method, url, attempt, delay, error.getMessage()));
                    Retry.sleep(delay);
                    continue;
                }
                throw ApiBrasilException.from(error);
            }

            hooks.fireResponse(new ResponseHookInfo(
                    method, url, response.status(),
                    Duration.ofNanos(System.nanoTime() - startedAt), attempt));

            if (response.status() >= 400) {
                ApiBrasilException error =
                        ErrorFactory.create(response.status(), response.data(), response.headers());
                boolean retryableStatus = retry.retryOnStatuses().contains(response.status());

                if (retryableStatus && attempt + 1 < maxAttempts) {
                    Duration delay = error instanceof RateLimitException rateLimit && rateLimit.retryAfter() != null
                            ? rateLimit.retryAfter()
                            : Retry.backoffDelay(attempt, retry);
                    attempt++;
                    hooks.fireRetry(new RetryHookInfo(
                            method, url, attempt, delay, "HTTP " + response.status()));
                    Retry.sleep(delay);
                    continue;
                }
                throw error;
            }

            return response.data();
        }
    }

    /**
     * Executa a requisição e devolve o corpo como objeto JSON.
     *
     * <p>Respostas vazias viram {@code {}}; respostas que não são objetos JSON
     * (listas, texto) são embrulhadas em {@code {"data": ...}}.
     */
    public Map<String, Object> requestJson(HttpMethod method, String path, Object body, RequestOptions options) {
        return Json.asMap(request(method, path, body, options));
    }

    public Map<String, Object> get(String path) {
        return get(path, RequestOptions.NONE);
    }

    public Map<String, Object> get(String path, RequestOptions options) {
        return requestJson(HttpMethod.GET, path, null, options);
    }

    public Map<String, Object> post(String path, Object body) {
        return post(path, body, RequestOptions.NONE);
    }

    public Map<String, Object> post(String path, Object body, RequestOptions options) {
        return requestJson(HttpMethod.POST, path, body, options);
    }

    public Map<String, Object> put(String path, Object body) {
        return put(path, body, RequestOptions.NONE);
    }

    public Map<String, Object> put(String path, Object body, RequestOptions options) {
        return requestJson(HttpMethod.PUT, path, body, options);
    }

    public Map<String, Object> patch(String path, Object body) {
        return patch(path, body, RequestOptions.NONE);
    }

    public Map<String, Object> patch(String path, Object body, RequestOptions options) {
        return requestJson(HttpMethod.PATCH, path, body, options);
    }

    public Map<String, Object> delete(String path) {
        return delete(path, null, RequestOptions.NONE);
    }

    public Map<String, Object> delete(String path, Object body) {
        return delete(path, body, RequestOptions.NONE);
    }

    public Map<String, Object> delete(String path, Object body, RequestOptions options) {
        return requestJson(HttpMethod.DELETE, path, body, options);
    }

    /** Baixa o corpo cru (PDF de boleto, imagens...). */
    public byte[] bytes(HttpMethod method, String path, Object body, RequestOptions options) {
        RequestOptions opts = (options == null ? RequestOptions.NONE : options)
                .withResponseType(ResponseType.BYTES);
        Object data = request(method, path, body, opts);
        if (data instanceof byte[] raw) {
            return raw;
        }
        if (data == null) {
            return new byte[0];
        }
        return String.valueOf(data).getBytes(StandardCharsets.UTF_8);
    }

    /** Fecha o transporte, quando ele foi criado pela SDK. */
    @Override
    public void close() {
        if (ownsTransport) {
            transport.close();
        }
    }

    private Map<String, String> buildHeaders(RequestOptions options) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("User-Agent", SDK_USER_AGENT);

        if (config.headers() != null) {
            headers.putAll(config.headers());
        }

        String bearer = options.bearerToken() != null ? options.bearerToken() : bearerToken;
        if (bearer != null && !bearer.isBlank()) {
            headers.put("Authorization", "Bearer " + bearer);
        }

        String device = options.deviceToken() != null ? options.deviceToken() : deviceToken;
        if (device != null && !device.isBlank()) {
            headers.put("DeviceToken", device);
        }

        String secret = options.secretKey() != null ? options.secretKey() : config.secretKey();
        if (secret != null && !secret.isBlank()) {
            headers.put("SecretKey", secret);
        }

        if (options.headers() != null) {
            headers.putAll(options.headers());
        }

        return headers;
    }

    /** Monta a query string a partir de um mapa, ignorando valores nulos. */
    public static String buildQueryString(Map<String, Object> query) {
        if (query == null || query.isEmpty()) {
            return "";
        }

        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Object> entry : query.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (value instanceof Collection<?> items) {
                value = items.stream().map(String::valueOf).collect(Collectors.joining(","));
            }
            buffer.append(buffer.length() == 0 ? '?' : '&')
                    .append(encode(entry.getKey()))
                    .append('=')
                    .append(encode(String.valueOf(value)));
        }
        return buffer.toString();
    }

    /** Junta a base da API com o caminho, sem barras duplicadas. */
    public static String joinUrl(String baseUrl, String path) {
        String base = baseUrl == null ? "" : baseUrl.replaceAll("/+$", "");
        String suffix = path == null ? "" : path.replaceAll("^/+", "");
        return suffix.isEmpty() ? base : base + "/" + suffix;
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    @SafeVarargs
    private static <T> T firstNonNull(T... values) {
        for (T value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
