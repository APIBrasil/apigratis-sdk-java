package com.apibrasil.sdk.dto.estados;

import com.apibrasil.sdk.dto.base.estado.Estado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EstadosRes {
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
        private List<Estado> estados;
    }
}