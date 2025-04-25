package com.sarlym.osmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoDTO {

    private String nome;
    private String especialidade;
    private String matricula;
    private Boolean ativo;

}
