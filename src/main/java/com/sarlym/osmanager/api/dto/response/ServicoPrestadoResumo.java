package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class ServicoPrestadoResumo {

    private ServicoDTO servico;
    private BigDecimal valorUnitario;
    private String observacoes;

}
