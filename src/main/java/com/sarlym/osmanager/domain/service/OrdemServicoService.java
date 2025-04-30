package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.repository.OrdemServicoRepository;

public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico buscaOrdemServicoOuErro (Long id) {
        return ordemServicoRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Ordem de servico com id %d nao encontrada", id)));
    }

    
}
