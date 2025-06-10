package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoResumo {

    private String codigo;
    private String nome;
    private BigDecimal valorUnitario;

}
