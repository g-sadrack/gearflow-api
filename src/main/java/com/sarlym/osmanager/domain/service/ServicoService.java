package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.mapper.ServicoMapper;
import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.api.dto.response.ServicoDTO;
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

    public ServicoDTO buscarServicoOuErro(Long id) {
        return servicoMapper.modelParaDTO(servicoRepository.findById(id).orElseThrow(
                () -> new NegocioException("Servico nao encontrado")));
    }

    public List<ServicoDTO> listarServicos() {
        return servicoMapper.modelLitaParaDTOLista(servicoRepository.findAll());
    }

    @Transactional
    public ServicoDTO cadastrarServico(ServicoRequest servicoRequest) {
        return servicoMapper.modelParaDTO(servicoRepository.save(servicoMapper.requestParaModel(servicoRequest)));
    }

    @Transactional
    public ServicoDTO alterarServico(Long id, ServicoRequest servicoRequest) {
        Servico servicoAntigo = servicoMapper.dtoParaModel(buscarServicoOuErro(id));
        Servico servico = servicoMapper.requestParaModel(servicoRequest);
        servico.setId(servicoAntigo.getId());
        return servicoMapper.modelParaDTO(servicoRepository.save(servico));
    }

    @Transactional
    public void excluirServico(Long id) {
        Servico servico = servicoMapper.dtoParaModel(buscarServicoOuErro(id));
        servicoRepository.delete(servico);
        servicoRepository.flush();
    }

}
