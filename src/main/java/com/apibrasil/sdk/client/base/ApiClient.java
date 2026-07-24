package com.apibrasil.sdk.client.base;

import com.apibrasil.sdk.exception.ApiException;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;

/**
 * Contrato do cliente HTTP da interface legada.
 *
 * @deprecated Prefira {@code new com.apibrasil.sdk.ApiBrasil(...)}, que cobre
 *             toda a plataforma com erros tipados, retry e hooks.
 */
@Deprecated(since = "0.1.0")
public interface ApiClient {
    <T> T executeApiCall(HttpUriRequest request, Class<T> responseType) throws ApiException;
    String getAuthToken();
    String getDeviceToken();
}
