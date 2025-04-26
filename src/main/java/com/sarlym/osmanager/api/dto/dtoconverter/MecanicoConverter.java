package com.sarlym.osmanager.api.dto.dtoconverter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.osmanager.api.dto.MecanicoDTO;
import com.sarlym.osmanager.domain.model.Mecanico;

@Component
public class MecanicoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public MecanicoDTO paraDTO(Mecanico mecanico) {
        return modelMapper.map(mecanico, MecanicoDTO.class);
    }

    public List<MecanicoDTO> paraDTOLista(List<Mecanico> mecanicos) {
        return mecanicos.stream().map(this::paraDTO).toList();
    }
    
}
