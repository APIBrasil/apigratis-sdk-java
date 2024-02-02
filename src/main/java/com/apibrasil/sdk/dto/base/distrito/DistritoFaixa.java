package com.apibrasil.sdk.dto.base.distrito;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DistritoFaixa {
    private String distrito;

    @JsonProperty("distrito_sem_acento")
    private String distritoSemAcento;

    private String estado;
    private String latitude;
    private String longitude;
}
