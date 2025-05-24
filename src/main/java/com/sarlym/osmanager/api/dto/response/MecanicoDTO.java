package com.sarlym.osmanager.api.dto.response;

import lombok.Data;

@Data
public class MecanicoDTO {

    private Long id;
    private String nome;
    private String especialidade;
    private String matricula;
    private Boolean ativo;

}
