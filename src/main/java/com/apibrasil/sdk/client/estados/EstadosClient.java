package com.apibrasil.sdk.client.estados;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.base.BaseApiClient;
import com.apibrasil.sdk.dto.estados.EstadosReq;
import com.apibrasil.sdk.dto.estados.EstadosRes;
import com.apibrasil.sdk.exception.ApiException;

public class EstadosClient extends BaseApiClient {

    private static final String ENDPOINT = "https://cluster.apigratis.com/api/v2/cep/estados";

    public EstadosClient(ApiClient apiClient) {
        super(apiClient);
    }

    public EstadosRes getEstados(EstadosReq request) throws ApiException {
        return executePost(ENDPOINT, request, EstadosRes.class);
    }
}