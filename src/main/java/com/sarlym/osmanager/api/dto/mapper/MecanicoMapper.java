package com.sarlym.osmanager.api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.api.dto.response.MecanicoDTO;
import com.sarlym.osmanager.domain.model.Mecanico;

@Component
public class MecanicoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Mecanico requestParaModel(MecanicoRequest mecanicoRequest) {
        return modelMapper.map(mecanicoRequest, Mecanico.class);
    }

    public MecanicoDTO modelParaDTO(Mecanico mecanico) {
        return modelMapper.map(mecanico, MecanicoDTO.class);
    }

    public List<MecanicoDTO> modelListaParaDTOLista(List<Mecanico> mecanicos) {
        return mecanicos.stream().map(this::modelParaDTO).toList();
    }

    public Mecanico dtoParaModel(MecanicoDTO mecanicoDTO) {
        return modelMapper.map(mecanicoDTO, Mecanico.class);
    }

}
