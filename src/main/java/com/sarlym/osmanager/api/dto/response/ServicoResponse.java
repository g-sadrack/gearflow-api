package com.sarlym.osmanager.api.dto.response;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.domain.model.Servico;

@Component
public class ServicoResponse {

    @Autowired
    private ModelMapper modelMapper;

    public Servico paraModel(ServicoRequest servicoRequest) {
        return modelMapper.map(servicoRequest, Servico.class);
    }
}
