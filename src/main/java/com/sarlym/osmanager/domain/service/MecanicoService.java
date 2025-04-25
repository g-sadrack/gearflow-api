package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.api.dto.response.MecanicoResponse;
import com.sarlym.osmanager.domain.exception.NegocioException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.repository.MecanicoRepository;

@Service
public class MecanicoService {

    private final MecanicoRepository mecanicoRepository;
    private final MecanicoResponse mecanicoResponse;

    @Autowired
    public MecanicoService(MecanicoRepository mecanicoRepository , MecanicoResponse mecanicoResponse) {
        this.mecanicoRepository = mecanicoRepository;
        this.mecanicoResponse = mecanicoResponse;
    }

    public Mecanico buscarMecanicoOuErro(Long id) {
        return mecanicoRepository.findById(id).orElseThrow(
                () -> new NegocioException("Mecanico nao encontrado"));
    }

    public List<Mecanico> listarMecanicos() {
        return mecanicoRepository.findAll();
    }

    public Mecanico cadastrarMecanico(MecanicoRequest mecanicoRequest) {
        System.out.println(mecanicoRequest);
        Mecanico mecanico =  mecanicoResponse.paraModel(mecanicoRequest);
        return mecanicoRepository.save(mecanico);
    }

    public Mecanico atualizarMecanico(Long id, MecanicoRequest mecanicoRequest) {
        Mecanico mecanico = buscarMecanicoOuErro(id);
        mecanico = mecanicoResponse.paraModel(mecanicoRequest);
        return mecanicoRepository.save(mecanico);
    }

    public void excluirMecanico(Long id) {
        Mecanico mecanico = buscarMecanicoOuErro(id);
        mecanicoRepository.delete(mecanico);
    }
}
