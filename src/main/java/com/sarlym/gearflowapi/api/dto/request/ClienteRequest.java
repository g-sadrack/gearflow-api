package com.sarlym.gearflowapi.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    private String nome;
    private String telefone;
    private String cpf;
    private String email;

}
