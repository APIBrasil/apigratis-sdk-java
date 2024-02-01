package com.apibrasil.sdk.dto.estados;

import lombok.Data;

import java.util.List;

@Data
public class EstadosResponse {
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

    @Data
    public static class Estado {
        private String sigla;
        private String faixa_ini;
        private String faixa_fim;
        private String estado;
        private String capital;
        private String regiao;
        private String estado_sem_acento;
        private String capital_sem_acento;
        private String regiao_sem_acento;
        private String latitude;
        private String longitude;
    }
}