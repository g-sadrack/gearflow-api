package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ServicoDTO {

    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal valor;
    private Integer tempo_medio;

}
