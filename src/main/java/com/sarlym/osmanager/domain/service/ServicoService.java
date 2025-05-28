package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.mapper.ServicoMapper;
import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Servico;
import com.sarlym.osmanager.domain.repository.ServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    private ServicoRepository servicoRepository;
    private ServicoMapper servicoMapper;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
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
        return servicoRepository.save(servicoMapper.requestParaModel(servicoRequest));
    }

    @Transactional
    public Servico alterarServico(Long id, ServicoRequest servicoRequest) {
        Servico servicoAntigo = buscarServicoOuErro(id);
        Servico servico = servicoMapper.requestParaModel(servicoRequest);
        servico.setId(servicoAntigo.getId());
        return servicoRepository.save(servico);
    }

    @Transactional
    public void excluirServico(Long id) {
        Servico servico = buscarServicoOuErro(id);
        servicoRepository.delete(servico);
        servicoRepository.flush();
    }

}
