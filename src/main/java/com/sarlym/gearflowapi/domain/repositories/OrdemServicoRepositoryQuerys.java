package com.sarlym.gearflowapi.domain.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sarlym.gearflowapi.api.core.enums.Status;
import com.sarlym.gearflowapi.domain.model.OrdemServico;

@Repository
public interface OrdemServicoRepositoryQuerys {

    List<OrdemServico> find(String numero_os, Status status, Long veiculoId, LocalDateTime dataInicio, LocalDateTime dataFim);
    
}
