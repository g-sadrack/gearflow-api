package com.sarlym.osmanager.api.dto.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.osmanager.api.dto.OrdemServicoDTO;
import com.sarlym.osmanager.domain.model.OrdemServico;

@Component
public class OrdemServicoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OrdemServicoDTO paraDto (OrdemServico ordemServico){
        return modelMapper.map(ordemServico, OrdemServicoDTO.class);
    }
}
