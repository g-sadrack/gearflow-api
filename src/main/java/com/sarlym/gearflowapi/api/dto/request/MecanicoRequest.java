package com.sarlym.gearflowapi.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoRequest {
    
    private String nome;
    private String especialidade;
    private String matricula;
    private Boolean ativo;

}
