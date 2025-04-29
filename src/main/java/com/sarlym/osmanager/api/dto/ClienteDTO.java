package com.sarlym.osmanager.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClienteDTO {
    
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private List<VeiculoDTO> veiculos;
}
