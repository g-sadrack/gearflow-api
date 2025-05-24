package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;

import com.sarlym.osmanager.domain.model.Servico;

import lombok.Data;

@Data
public class ItemServicoResumo {

    private Servico servico;
    private BigDecimal valorUnitario;
    private String observacoes;

}
