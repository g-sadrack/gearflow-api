package com.sarlym.gearflowapi.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoPrestadoRequest {

    private Long servico;
    private BigDecimal valorServicoPrestado;
    private String observacoes;

}
