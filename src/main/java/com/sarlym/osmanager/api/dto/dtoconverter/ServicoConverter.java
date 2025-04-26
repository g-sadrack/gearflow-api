package com.sarlym.osmanager.api.dto.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.sarlym.osmanager.api.dto.ServicoDTO;
import com.sarlym.osmanager.domain.model.Servico;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ServicoConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public ServicoDTO paraDTO(Servico servico) {
        return modelMapper.map(servico, ServicoDTO.class);
    }

    public List<ServicoDTO> paraDTOLista(List<Servico> servicos) {
        return servicos.stream().map(this::paraDTO).toList();
    }
    
}