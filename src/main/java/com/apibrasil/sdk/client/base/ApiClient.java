package com.apibrasil.sdk.client.base;

import com.apibrasil.sdk.exception.ApiException;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;

public interface ApiClient {
    <T> T executeApiCall(HttpUriRequest request, Class<T> responseType) throws ApiException;
    String getAuthToken();
    String getDeviceToken();
}
