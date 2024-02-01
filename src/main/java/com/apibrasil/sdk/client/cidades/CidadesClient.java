package com.apibrasil.sdk.client.cidades;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.cidades.CidadesReq;
import com.apibrasil.sdk.dto.cidades.CidadesRes;
import com.apibrasil.sdk.exception.ApiException;

public class CidadesClient extends BaseApiClient {

    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/cep/cidades";

    public CidadesClient(ApiClient apiClient) {
        super(apiClient);
    }

    public CidadesRes getCidades(CidadesReq request) throws ApiException {
        return executePost(ENDPOINT, request, CidadesRes.class);
    }
}