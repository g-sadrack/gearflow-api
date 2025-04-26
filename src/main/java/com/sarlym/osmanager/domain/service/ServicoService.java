package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.api.dto.response.ServicoResponse;
import com.sarlym.osmanager.domain.exception.NegocioException;
import com.sarlym.osmanager.domain.model.Servico;
import com.sarlym.osmanager.domain.repository.ServicoRepository;

@Service
public class ServicoService {
    
    private ServicoRepository servicoRepository;
    private ServicoResponse servicoResponse;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository, ServicoResponse servicoResponse) {
        this.servicoRepository = servicoRepository;
        this.servicoResponse = servicoResponse;
    }

    public Servico buscarServicoOuErro(Long id) {
        return servicoRepository.findById(id).orElseThrow(
                () -> new NegocioException("Servico nao encontrado"));
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico cadastrarServico(ServicoRequest servicoRequest) {
        return servicoRepository.save(servicoResponse.paraModel(servicoRequest));
    }

    public Servico alterarServico(Long id, ServicoRequest servicoRequest) {
       Servico servicoAntigo = buscarServicoOuErro(id);
       Servico servico = servicoResponse.paraModel(servicoRequest);
       servico.setId(servicoAntigo.getId());
       return servicoRepository.save(servico);
    }

    public void excluirServico(Long id) {
        Servico servico = buscarServicoOuErro(id);
        servicoRepository.delete(servico);
    }
    
}
