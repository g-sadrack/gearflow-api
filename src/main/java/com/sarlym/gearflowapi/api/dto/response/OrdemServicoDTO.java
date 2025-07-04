package com.sarlym.gearflowapi.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sarlym.gearflowapi.api.core.enums.Status;

import lombok.Data;

@Data
public class OrdemServicoDTO {

    private Long id;
    private String numeroOs;
    private Status status;
    private String descricaoProblema;
    private String diagnosticoTecnico;
    private BigDecimal valorTotal;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataFinalizacao;
    private Boolean ativo;
    private VeiculoResumo veiculo;
    private MecanicoResumo mecanico;
    private List<ServicoPrestadoResumo> servicos;
    private List<ProdutoOrdemServicoResumo> produtos;

}
