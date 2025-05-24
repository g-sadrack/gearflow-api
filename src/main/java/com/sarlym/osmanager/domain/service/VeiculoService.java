package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.mapper.VeiculoMapper;
import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
import com.sarlym.osmanager.api.dto.response.VeiculoDTO;
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
    public VeiculoDTO buscarVeiculoOuErro(Long id) {
        return veiculoMapper.modeloParaDTO(veiculoRepository.findById(id)
                .orElseThrow(() -> new NegocioException(String.format(VEICULO_NAO_ENCONTRADO, id))));
    }

    public List<VeiculoDTO> listarVeiculos() {
        return veiculoMapper.modelListaParaDTOLista(veiculoRepository.findAll());
    }

    @Transactional
    public VeiculoDTO cadastrarVeiculo(VeiculoRequest veiculoRequest) {
        Veiculo veiculo = veiculoMapper.requestParaModel(veiculoRequest);
        return veiculoMapper.modeloParaDTO(veiculoRepository.save(veiculo));
    }

    @Transactional
    public VeiculoDTO alterarVeiculo(Long id, VeiculoRequest veiculoRequest) {
        Veiculo veiculoAntigo = veiculoMapper.dtoParaModel(buscarVeiculoOuErro(id));
        Veiculo veiculo = veiculoMapper.requestParaModel(veiculoRequest);
        veiculo.setId(veiculoAntigo.getId());
        return veiculoMapper.modeloParaDTO(veiculoRepository.save(veiculo));
    }

    @Transactional
    public void deletarVeiculo(Long id) {
        Veiculo veiculo = veiculoMapper.dtoParaModel(buscarVeiculoOuErro(id));
        veiculoRepository.delete(veiculo);
        veiculoRepository.flush();
    }

}
