package com.apibrasil.sdk.client.login;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.login.LoginRequest;
import com.apibrasil.sdk.dto.login.LoginResponse;
import com.apibrasil.sdk.exception.ApiException;

public class LoginClient extends BaseApiClient {
    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/login";

    public LoginClient(ApiClient apiClient) {
        super(apiClient);
    }

    public LoginResponse login(LoginRequest request) throws ApiException {
        return executePost(ENDPOINT, request, LoginResponse.class);
    }
}
