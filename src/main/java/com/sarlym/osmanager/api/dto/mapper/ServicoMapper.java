package com.sarlym.osmanager.api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.api.dto.response.ServicoDTO;
import com.sarlym.osmanager.domain.model.Servico;

@Component
public class ServicoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Servico requestParaModel(ServicoRequest servicoRequest) {
        return modelMapper.map(servicoRequest, Servico.class);
    }

    public ServicoDTO modelParaDTO(Servico servico) {
        return modelMapper.map(servico, ServicoDTO.class);
    }

    public List<ServicoDTO> modelLitaParaDTOLista(List<Servico> servicos) {
        return servicos.stream().map(this::modelParaDTO).toList();
    }

    public Servico dtoParaModel(ServicoDTO servicoDTO) {
        return modelMapper.map(servicoDTO, Servico.class);
    }
}
