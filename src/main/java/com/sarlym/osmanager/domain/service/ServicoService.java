package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.mapper.ServicoMapper;
import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.domain.exception.NegocioException;
import com.sarlym.osmanager.domain.model.Servico;
import com.sarlym.osmanager.domain.repository.ServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    private ServicoRepository servicoRepository;
    private ServicoMapper servicoMapper;

    public ServicoService(ServicoRepository servicoRepository, ServicoMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.servicoMapper = servicoMapper;
    }

    public Servico buscarServicoOuErro(Long id) {
        return servicoRepository.findById(id).orElseThrow(
                () -> new NegocioException("Servico nao encontrado"));
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico cadastrarServico(ServicoRequest servicoRequest) {
        return servicoRepository.save(servicoMapper.paraModel(servicoRequest));
    }

    @Transactional
    public Servico alterarServico(Long id, ServicoRequest servicoRequest) {
        Servico servicoAntigo = buscarServicoOuErro(id);
        Servico servico = servicoMapper.paraModel(servicoRequest);
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
