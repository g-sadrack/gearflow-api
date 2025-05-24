package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sarlym.osmanager.api.core.enums.Status;

import lombok.Data;

@Data
public class OrdemServicoResumo {

    private String numero_os;
    private Status status;
    private BigDecimal valorTotal;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFinalizacao;
    private VeiculoResumo veiculo;
    private MecanicoResumo mecanico;

}
