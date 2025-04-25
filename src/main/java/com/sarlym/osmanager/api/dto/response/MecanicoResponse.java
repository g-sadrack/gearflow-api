package com.sarlym.osmanager.api.dto.response;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.domain.model.Mecanico;

@Controller
public class MecanicoResponse {

    @Autowired
    private ModelMapper modelMapper;

    public Mecanico paraModel(MecanicoRequest mecanicoRequest) {
        return modelMapper.map(mecanicoRequest, Mecanico.class);
    }
}
