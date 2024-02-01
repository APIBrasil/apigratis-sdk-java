package com.apibrasil.sdk.dto.base.estado;

import lombok.Data;

@Data
public class Estado {
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