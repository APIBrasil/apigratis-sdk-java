package com.apibrasil.sdk.client.login;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.login.LoginReq;
import com.apibrasil.sdk.dto.login.LoginRes;
import com.apibrasil.sdk.exception.ApiException;

/**
 * Cliente legado.
 *
 * @deprecated Prefira {@code new com.apibrasil.sdk.ApiBrasil(...)}.
 */
@Deprecated(since = "0.1.0")
public class LoginClient extends BaseApiClient {
    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/login";

    public LoginClient(ApiClient apiClient) {
        super(apiClient);
    }

    public LoginRes login(LoginReq request) throws ApiException {
        return executePost(ENDPOINT, request, LoginRes.class);
    }
}
