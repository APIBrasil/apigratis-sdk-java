package com.apibrasil.sdk.dto.cep;

import com.apibrasil.sdk.dto.base.cep.CepDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CepRes {
    private boolean error;
    private String message;

    @JsonProperty("api_limit")
    private int apiLimit;

    @JsonProperty("api_limit_for")
    private String apiLimitFor;

    @JsonProperty("api_limit_used")
    private int apiLimitUsed;

    @Data
    public static class CepResponseData {
        private CepDetails cep;
    }
}
