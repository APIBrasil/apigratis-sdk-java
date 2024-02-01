package com.apibrasil.sdk.dto.bairros;

import com.apibrasil.sdk.dto.base.bairro.Bairro;
import com.apibrasil.sdk.dto.base.cidade.Cidade;
import lombok.Data;
import java.util.List;

@Data
public class BairrosResponse {
    private boolean error;
    private String message;
    private ResponseData response;
    private int api_limit;
    private String api_limit_for;
    private int api_limit_used;

    @Data
    public static class ResponseData {
        private Cidade cidade;
        private List<Bairro> bairros;
    }
}