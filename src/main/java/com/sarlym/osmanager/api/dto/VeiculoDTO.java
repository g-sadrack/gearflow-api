package com.sarlym.osmanager.api.dto;

import lombok.Data;

@Data
public class VeiculoDTO {

    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private Integer quilometragem;

}
