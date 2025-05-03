package com.sarlym.osmanager.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.domain.model.ItemPeca;
import com.sarlym.osmanager.domain.model.ItemServico;

import lombok.Data;

@Data
public class OrdemServicoDTO {

    private Long id;
    private String numero_os;
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
    private List<ItemServico> servicos;
    private List<ItemPeca> pecas;

}
