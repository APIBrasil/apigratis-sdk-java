package com.apibrasil.sdk.dto.base.bairro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bairro {
    private String bairro;
    @JsonProperty("bairro_sem_acento")
    private String bairroSemAcento;
    private String estado;
    private String latitude;
    private String longitude;
    @JsonProperty("bairro_faixa")
    private BairroFaixa bairroFaixa;
}