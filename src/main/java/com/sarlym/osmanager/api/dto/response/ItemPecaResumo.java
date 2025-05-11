package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemPecaResumo {

    private String codigo;
    private String nome;
    private BigDecimal valorUnitario;
    private Integer quantidadeEstoque;
    private Integer quantidadeEstoqueMinimo;

}
