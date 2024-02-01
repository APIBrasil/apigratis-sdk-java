package com.apibrasil.sdk.dto.estados;

import com.apibrasil.sdk.dto.base.estado.Estado;
import lombok.Data;

import java.util.List;

@Data
public class EstadosRes {
    private boolean error;
    private String message;
    private ResponseData response;
    private int api_limit;
    private String api_limit_for;
    private int api_limit_used;

    @Data
    public static class ResponseData {
        private List<Estado> estados;
    }
}