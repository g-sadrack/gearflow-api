package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoDTO {

    private Long id;
    private String codigo;
    private String nome;
    private BigDecimal valor;
    
}
