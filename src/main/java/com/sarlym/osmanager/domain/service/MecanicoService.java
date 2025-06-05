package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.mapper.MecanicoMapper;
import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.repositories.MecanicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MecanicoService {

    private static final String ACAO_NAO_PODE_SER_REALIZADA_MECANICO_NAO_ENCONTRADO = "Mecanico com id %d, nao encontrado";
    private final MecanicoRepository mecanicoRepository;
    private final MecanicoMapper mecanicoMapper;

    public MecanicoService(MecanicoRepository mecanicoRepository, MecanicoMapper mecanicoMapper) {
        this.mecanicoRepository = mecanicoRepository;
        this.mecanicoMapper = mecanicoMapper;
    }

    public Mecanico buscarMecanicoOuErro(Long id) {
        return mecanicoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(ACAO_NAO_PODE_SER_REALIZADA_MECANICO_NAO_ENCONTRADO, id)));
    }

    public List<Mecanico> listarMecanicos() {
        return mecanicoRepository.findAll();
    }

    @Transactional
    public Mecanico cadastrarMecanico(MecanicoRequest mecanicoRequest) {
        Mecanico mecanico = mecanicoMapper.requestParaModel(mecanicoRequest);
        return mecanicoRepository.save(mecanico);
    }

    @Transactional
    public Mecanico atualizarMecanico(Long id, MecanicoRequest mecanicoRequest) {
        Mecanico mecanicoAntigo = buscarMecanicoOuErro(id);
        Mecanico mecanico = mecanicoMapper.requestParaModel(mecanicoRequest);
        mecanico.setId(mecanicoAntigo.getId());
        return mecanicoRepository.save(mecanico);
    }

    @Transactional
    public void excluirMecanico(Long id) {
        Mecanico mecanico = buscarMecanicoOuErro(id);
        mecanicoRepository.delete(mecanico);
        mecanicoRepository.flush();
    }
}
