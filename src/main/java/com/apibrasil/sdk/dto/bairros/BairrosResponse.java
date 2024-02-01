package com.apibrasil.sdk.dto.bairros;

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

    @Data
    public static class Cidade {
        private String cidade;
        private String cidade_sem_acento;
        private String estado;
        private String cidade_ibge;
        private String ddd;
        private String cidade_area;
        private String latitude;
        private String longitude;
    }

    @Data
    public static class Bairro {
        private String bairro;
        private String bairro_sem_acento;
        private String estado;
        private String latitude;
        private String longitude;
    }

}