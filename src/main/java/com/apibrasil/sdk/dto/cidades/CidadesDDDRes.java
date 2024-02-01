package com.apibrasil.sdk.dto.cidades;

import com.apibrasil.sdk.dto.base.cidade.Cidade;
import lombok.Data;

import java.util.List;


@Data
public class CidadesDDDRes {
    private boolean error;
    private String message;
    private ResponseData response;
    private int api_limit;
    private String api_limit_for;
    private int api_limit_used;

    @Data
    public static class ResponseData {
        private List<Cidade> cidades;
    }
}
