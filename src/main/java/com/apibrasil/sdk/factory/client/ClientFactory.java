package com.apibrasil.sdk.factory.client;

import com.apibrasil.sdk.client.ApiClientImpl;
import com.apibrasil.sdk.client.base.ApiClient;

public class ClientFactory {
    public static ApiClient createDefaultClient() {
        return new ApiClientImpl();
    }
}
