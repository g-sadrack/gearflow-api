package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.mapper.VeiculoMapper;
import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
import com.sarlym.osmanager.domain.exception.NegocioException;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repository.VeiculoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private static final String VEICULO_NAO_ENCONTRADO = "Veiculo com id %d nao encontrado";
    private final VeiculoRepository veiculoRepository;
    private final VeiculoMapper veiculoMapper;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    @Transactional
    public Veiculo buscarVeiculoOuErro(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new NegocioException(String.format(VEICULO_NAO_ENCONTRADO, id)));
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    @Transactional
    public Veiculo cadastrarVeiculo(VeiculoRequest veiculoRequest) {
        Veiculo veiculo = veiculoMapper.requestParaModel(veiculoRequest);
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public Veiculo alterarVeiculo(Long id, VeiculoRequest veiculoRequest) {
        Veiculo veiculo = buscarVeiculoOuErro(id);
        veiculoMapper.copiaParaNovo(veiculoRequest, veiculo);
        return veiculoRepository.save(veiculo);
    }

}