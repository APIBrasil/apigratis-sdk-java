package com.apibrasil.sdk.dto.base.cidade;

import com.apibrasil.sdk.dto.base.bairro.Bairro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Cidade {
    private String cidade;

    @JsonProperty("cidade_sem_acento")
    private String cidadeSemAcento;

    private String estado;

    @JsonProperty("cidade_ibge")
    private String cidadeIbge;

    private String ddd;

    @JsonProperty("cidade_area")
    private String cidadeArea;

    private String latitude;
    private String longitude;

    private List<Bairro> bairros;

    @JsonProperty("cidade_faixa")
    private CidadeFaixa cidadeFaixa;
}
