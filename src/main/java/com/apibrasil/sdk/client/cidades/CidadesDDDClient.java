package com.apibrasil.sdk.client.cidades;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.cidades.CidadesDDDReq;
import com.apibrasil.sdk.dto.cidades.CidadesDDDRes;
import com.apibrasil.sdk.exception.ApiException;

public class CidadesDDDClient extends BaseApiClient {

    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/cep/cidadesPorDDD";

    public CidadesDDDClient(ApiClient apiClient) {
        super(apiClient);
    }

    public CidadesDDDRes getCidadesPorDDD(CidadesDDDReq request) throws ApiException {
        return executePost(ENDPOINT, request, CidadesDDDRes.class);
    }
}
