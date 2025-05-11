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

    public Mecanico RequestparaModel(MecanicoRequest mecanicoRequest) {
        return modelMapper.map(mecanicoRequest, Mecanico.class);
    }

    public MecanicoDTO ModelParaDTO(Mecanico mecanico) {
        return modelMapper.map(mecanico, MecanicoDTO.class);
    }

    public List<MecanicoDTO> ModelListaParaDTOLista(List<Mecanico> mecanicos) {
        return mecanicos.stream().map(this::ModelParaDTO).toList();
    }

    public Mecanico DTOParaModel(MecanicoDTO mecanicoDTO) {
        return modelMapper.map(mecanicoDTO, Mecanico.class);
    }

}
