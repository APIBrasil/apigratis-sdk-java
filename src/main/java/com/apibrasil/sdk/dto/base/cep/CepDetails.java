package com.apibrasil.sdk.dto.base.cep;

import com.apibrasil.sdk.dto.base.bairro.Bairro;
import com.apibrasil.sdk.dto.base.cidade.Cidade;
import com.apibrasil.sdk.dto.base.complemento.Complemento;
import com.apibrasil.sdk.dto.base.distrito.Distrito;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CepDetails {
    private String cep;
    private String tipo;

    @JsonProperty("nome_logradouro")
    private String nomeLogradouro;

    private String logradouro;

    @JsonProperty("distrito_id")
    private String distritoId;

    private String estado;

    @JsonProperty("tipo_sem_acento")
    private String tipoSemAcento;

    @JsonProperty("nome_logradouro_sem_acento")
    private String nomeLogradouroSemAcento;

    @JsonProperty("logradouro_sem_acento")
    private String logradouroSemAcento;

    private String latitude;
    private String longitude;

    @JsonProperty("cep_ativo")
    private String cepAtivo;

    private Cidade cidade;
    private Bairro bairro;
    private List<Complemento> complemento;
    private Distrito distrito;
}
