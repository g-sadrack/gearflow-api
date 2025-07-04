package com.sarlym.gearflowapi.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PecaOrdemServicoRequest {

    private Long produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;

}
