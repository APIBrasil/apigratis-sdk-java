package com.apibrasil.sdk.client.base;

import com.apibrasil.sdk.exception.ApiException;
import com.apibrasil.sdk.util.JsonParser;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public abstract class BaseApiClient {
    protected final ApiClient apiClient;
    protected String authorizationToken;

    public BaseApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    protected <T> T executePost(String url, Object requestObj, Class<T> responseType) throws ApiException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("Authorization", "Bearer " + this.authorizationToken);

        try {
            String json = JsonParser.toJson(requestObj);
            postRequest.setEntity(new StringEntity(json));
            return apiClient.executeApiCall(postRequest, responseType);
        } catch (IOException e) {
            throw new ApiException("Error while executing POST request", e);
        }
    }
}
