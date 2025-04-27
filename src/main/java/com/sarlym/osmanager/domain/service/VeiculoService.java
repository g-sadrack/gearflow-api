package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
import com.sarlym.osmanager.api.dto.response.VeiculoResponse;
import com.sarlym.osmanager.domain.exception.NegocioException;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoResponse veiculoResponse;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoResponse veiculoResponse) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoResponse = veiculoResponse;
    }

    public Veiculo buscarVeiculoOuErro(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new NegocioException("Veiculo nao encontrado"));
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo cadastrarVeiculo(VeiculoRequest veiculoRequest) {
        Veiculo veiculo = veiculoResponse.paraModel(veiculoRequest);
        return veiculoRepository.save(veiculo);
    }

    public Veiculo alterarVeiculo(Long id, VeiculoRequest veiculoRequest) {
        Veiculo veiculoAntigo = buscarVeiculoOuErro(id);
        Veiculo veiculo = veiculoResponse.paraModel(veiculoRequest);
        veiculo.setId(veiculoAntigo.getId());
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

}
