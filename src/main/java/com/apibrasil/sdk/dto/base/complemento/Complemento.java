package com.apibrasil.sdk.dto.base.complemento;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Complemento {
    private String cep;
    private String complemento;
    @JsonProperty("complemento_sem_acento")
    private String complementoSemAcento;
}
