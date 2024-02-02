package com.apibrasil.sdk.client.cep;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.cep.CepReq;
import com.apibrasil.sdk.dto.cep.CepRes;
import com.apibrasil.sdk.exception.ApiException;

public class CepClient extends BaseApiClient {

    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/cep";

    public CepClient(ApiClient apiClient) {
        super(apiClient);
    }

    public CepRes getCepInfo(CepReq request) throws ApiException {
        return executePost(ENDPOINT, request, CepRes.class);
    }
}