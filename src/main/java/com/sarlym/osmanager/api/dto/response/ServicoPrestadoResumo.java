package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class ServicoPrestadoResumo {

    private ServicoResumo servico;
    private BigDecimal valorServicoPrestado;
    private String observacoes;

}
