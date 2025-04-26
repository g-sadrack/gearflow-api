package com.sarlym.osmanager.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoRequest {
    private String codigo;
    private String descricao;
    private BigDecimal valor;
    private Integer tempo_medio;
}
