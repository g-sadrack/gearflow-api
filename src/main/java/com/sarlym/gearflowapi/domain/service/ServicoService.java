package com.sarlym.gearflowapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.gearflowapi.api.dto.mapper.ServicoMapper;
import com.sarlym.gearflowapi.api.dto.request.ServicoRequest;
import com.sarlym.gearflowapi.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.gearflowapi.domain.model.Servico;
import com.sarlym.gearflowapi.domain.repositories.ServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    private ServicoRepository servicoRepository;
    private ServicoMapper servicoMapper;

    public ServicoService(ServicoRepository servicoRepository, ServicoMapper servicoMapper ) {
        this.servicoRepository = servicoRepository;
        this.servicoMapper = servicoMapper;
    }

    public Servico buscarServicoOuErro(Long id) {
        return servicoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Serviço não encontrado"));
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico cadastrarServico(ServicoRequest servicoRequest) {
        Servico servico = servicoMapper.requestParaModel(servicoRequest);
        return servicoRepository.save(servico);
    }

    @Transactional
    public Servico alterarServico(Long id, ServicoRequest servicoRequest) {
        Servico servico = buscarServicoOuErro(id);
        servicoMapper.copiaParaNovo(servicoRequest, servico);
        return servicoRepository.save(servico);
    }

    @Transactional
    public void excluirServico(Long id) {
        Servico servico = buscarServicoOuErro(id);
        servicoRepository.delete(servico);
        servicoRepository.flush();
    }

}
