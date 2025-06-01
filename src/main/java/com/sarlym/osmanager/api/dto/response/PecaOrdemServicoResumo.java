package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;

import com.sarlym.osmanager.domain.model.Produto;

import lombok.Data;

@Data
public class PecaOrdemServicoResumo {

    private Long id;
    private Produto peca;
    private Integer quantidade;
    private BigDecimal valorUnitario;

}
