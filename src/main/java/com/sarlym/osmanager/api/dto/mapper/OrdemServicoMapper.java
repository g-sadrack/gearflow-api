package com.sarlym.osmanager.api.dto.mapper;

import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.api.dto.response.OrdemServicoDTO;
import com.sarlym.osmanager.api.dto.response.OrdemServicoResumo;
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

    public OrdemServicoResumo paraDTOResumo(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoResumo.class);
    }

    public List<OrdemServicoResumo> paraListaDTOResumo(List<OrdemServico> ordens) {
        return ordens.stream().map(this::paraDTOResumo).toList();
    }
    
    public OrdemServico paraModelo(OrdemServicoRequest request) {
        return modelMapper.map(request, OrdemServico.class);
    }

}
