package com.sarlym.osmanager.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.domain.model.OrdemServico;

@Repository
public interface OrdemServicoRepositoryQuerys {

    List<OrdemServico> find(String numero_os, Status status, Long veiculoId, LocalDateTime dataInicio, LocalDateTime dataFim);
    
}
