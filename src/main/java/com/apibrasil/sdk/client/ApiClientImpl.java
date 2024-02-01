package com.apibrasil.sdk.client;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.exception.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class ApiClientImpl implements ApiClient {
    private final CloseableHttpClient httpClient;

    @Getter
    @Setter
    private String authToken;

    @Setter
    private String deviceToken;

    public ApiClientImpl() {
        this.httpClient = HttpClients.createDefault();
    }


    @Override
    public <T> T executeApiCall(HttpUriRequest request, Class<T> responseType) throws ApiException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return parseResponse(jsonResponse, responseType);
        } catch (IOException | ParseException e) {
            throw new ApiException("Erro ao executar a chamada à API", e);
        }
    }

    @Override
    public String getDeviceToken() {
        return this.deviceToken;
    }


    private <T> T parseResponse(String jsonResponse, Class<T> responseType) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonResponse, responseType);
    }
}
