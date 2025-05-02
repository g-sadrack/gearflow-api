package com.sarlym.osmanager.api.dto.mapper;

import com.sarlym.osmanager.api.dto.response.OrdemServicoDTO;
import com.sarlym.osmanager.domain.model.OrdemServico;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdemServicoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrdemServicoDTO paraDTO(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoDTO.class);
    }

    public List<OrdemServicoDTO> paraListaDTO(List<OrdemServico> ordens) {
        return ordens.stream().map(this::paraDTO).toList();
    }

}
