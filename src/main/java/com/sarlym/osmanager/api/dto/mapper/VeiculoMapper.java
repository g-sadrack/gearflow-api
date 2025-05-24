package com.sarlym.osmanager.api.dto.mapper;

import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
import com.sarlym.osmanager.api.dto.response.VeiculoDTO;
import com.sarlym.osmanager.domain.model.Veiculo;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Veiculo requestParaModel(VeiculoRequest veiculoRequest) {
        return modelMapper.map(veiculoRequest, Veiculo.class);
    }

    public VeiculoDTO modeloParaDTO(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    public List<VeiculoDTO> modelListaParaDTOLista(List<Veiculo> veiculos) {
        return veiculos.stream().map(this::modeloParaDTO).toList();
    }

    public Veiculo dtoParaModel(VeiculoDTO veiculoDTO) {
        return modelMapper.map(veiculoDTO, Veiculo.class);
    }

}
