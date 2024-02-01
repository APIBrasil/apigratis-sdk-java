package com.apibrasil.sdk.dto.base.cidade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CidadeFaixa {
    private String cidade;
    private String estado;
    @JsonProperty("faixa_ini")
    private String faixaIni;
    @JsonProperty("faixa_fim")
    private String faixaFim;
}
