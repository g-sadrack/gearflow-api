package com.sarlym.osmanager.domain.service;

import org.springframework.stereotype.Service;

import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService (OrdemServicoRepository ordemServicoRepository){
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public OrdemServico buscaOrdemServicoOuErro (Long id) {
        return ordemServicoRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Ordem de servico com id %d nao encontrada", id)));
    }
    
}
