package com.apibrasil.sdk.dto.base.bairro;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BairroFaixa {
    @JsonProperty("faixa_ini")
    private String faixaIni;
    @JsonProperty("faixa_fim")
    private String faixaFim;
}
