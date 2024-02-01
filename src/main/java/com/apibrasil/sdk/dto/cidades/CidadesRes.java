package com.apibrasil.sdk.dto.cidades;

import com.apibrasil.sdk.dto.base.cidade.Cidade;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CidadesRes {
    private boolean error;
    private String message;
    private ResponseData response;
    @JsonProperty("api_limit")
    private int apiLimit;
    @JsonProperty("api_limit_for")
    private String apiLimitFor;
    @JsonProperty("api_limit_used")
    private int apiLimitUsed;

    @Data
    public static class ResponseData {
        private List<Cidade> cidades;
    }
}
