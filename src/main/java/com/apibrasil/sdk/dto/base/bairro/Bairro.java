package com.apibrasil.sdk.dto.base.bairro;

import lombok.Data;

@Data
public class Bairro {
    private String bairro;
    private String bairro_sem_acento;
    private String estado;
    private String latitude;
    private String longitude;
}