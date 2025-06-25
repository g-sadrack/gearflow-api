package com.sarlym.gearflowapi.api.dto.response;


import lombok.Data;

@Data
public class ServicoDTO {

    private Long id;
    private String codigo;
    private String descricao;
    private Integer tempo_medio;

}
