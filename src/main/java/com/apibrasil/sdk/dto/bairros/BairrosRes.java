package com.apibrasil.sdk.dto.bairros;

import com.apibrasil.sdk.dto.base.bairro.Bairro;
import com.apibrasil.sdk.dto.base.cidade.Cidade;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class BairrosRes {
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
        private Cidade cidade;
        private List<Bairro> bairros;
    }
}