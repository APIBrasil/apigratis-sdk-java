package com.apibrasil.sdk.factory.client;

import com.apibrasil.sdk.client.ApiClientImpl;
import com.apibrasil.sdk.client.base.ApiClient;

/**
 * Fábrica do cliente legado.
 *
 * @deprecated Prefira {@code new com.apibrasil.sdk.ApiBrasil(...)}.
 */
@Deprecated(since = "0.1.0")
public class ClientFactory {
    public static ApiClient createDefaultClient() {
        return new ApiClientImpl();
    }
}
