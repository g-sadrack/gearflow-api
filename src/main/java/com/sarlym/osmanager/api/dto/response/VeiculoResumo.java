package com.sarlym.osmanager.api.dto.response;

import lombok.Data;

@Data
public class VeiculoResumo {

    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private Integer quilometragem;
    private ClienteResumo cliente;
    
}
