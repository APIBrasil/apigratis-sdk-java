package com.apibrasil.sdk.dto.base.estado;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Estado {
    private String sigla;

    @JsonProperty("faixa_ini")
    private String faixaIni;

    @JsonProperty("faixa_fim")
    private String faixaFim;

    private String estado;
    private String capital;
    private String regiao;

    @JsonProperty("estado_sem_acento")
    private String estadoSemAcento;

    @JsonProperty("capital_sem_acento")
    private String capitalSemAcento;

    @JsonProperty("regiao_sem_acento")
    private String regiaoSemAcento;

    private String latitude;
    private String longitude;
}
