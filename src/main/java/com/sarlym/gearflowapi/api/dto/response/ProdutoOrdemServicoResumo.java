package com.sarlym.gearflowapi.api.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoOrdemServicoResumo {

    private ProdutoResumo produto;
    private Integer quantidade;
    private BigDecimal valor;

}
