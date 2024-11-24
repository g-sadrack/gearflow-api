package com.sarlym.osmanager.api.dto.response;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClienteResponse {
    @Autowired
    private ModelMapper modelMapper;

    public Cliente paraModel(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

}
