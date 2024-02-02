package com.apibrasil.sdk.client.bairros;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.bairros.BairrosReq;
import com.apibrasil.sdk.dto.bairros.BairrosRes;
import com.apibrasil.sdk.exception.ApiException;

public class BairrosClient extends BaseApiClient {
    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/cep/bairros";

    public BairrosClient(ApiClient apiClient) {
        super(apiClient);
    }

    public BairrosRes bairros(BairrosReq request) throws ApiException {
        return executePost(ENDPOINT, request, BairrosRes.class);
    }
}
