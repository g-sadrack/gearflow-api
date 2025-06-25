package com.sarlym.gearflowapi.api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.gearflowapi.api.dto.request.OrdemServicoRequest;
import com.sarlym.gearflowapi.api.dto.response.OrdemServicoDTO;
import com.sarlym.gearflowapi.api.dto.response.OrdemServicoResumo;
import com.sarlym.gearflowapi.domain.model.OrdemServico;

@Component
public class OrdemServicoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrdemServicoDTO modeloParaDTO(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoDTO.class);
    }

    public OrdemServicoDTO resumoParaDTO(OrdemServicoResumo ordemServicoResumo) {
        return modelMapper.map(ordemServicoResumo, OrdemServicoDTO.class);
    }

    public List<OrdemServicoDTO> modeloListaResumoParaDTOLista(List<OrdemServicoResumo> ordens) {
        return ordens.stream().map(this::resumoParaDTO).toList();
    }

    public OrdemServicoResumo modeloParaDTOResumo(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoResumo.class);
    }

    public List<OrdemServicoResumo> modeloListaParaListaDTOResumo(List<OrdemServico> ordens) {
        return ordens.stream().map(this::modeloParaDTOResumo).toList();
    }

    public OrdemServico requestParaModelo(OrdemServicoRequest request) {
        return modelMapper.map(request, OrdemServico.class);
    }

    public OrdemServico dtoParaModelo(OrdemServicoDTO ordemServicoDTO) {
        return modelMapper.map(ordemServicoDTO, OrdemServico.class);
    }

}
