package com.apibrasil.sdk.dto.base.cidade;

import com.apibrasil.sdk.dto.base.bairro.Bairro;
import lombok.Data;

import java.util.List;

@Data
public class Cidade {
    private String cidade;
    private String cidade_sem_acento;
    private String estado;
    private String cidade_ibge;
    private String ddd;
    private String cidade_area;
    private String latitude;
    private String longitude;
    private List<Bairro> bairros;
}
