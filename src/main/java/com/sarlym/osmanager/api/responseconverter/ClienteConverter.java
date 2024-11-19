package com.sarlym.osmanager.api.responseconverter;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClienteConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Cliente paraModelo(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public void copiaDTOparaModeloDominio(ClienteRequest clienteRequest, Cliente cliente){
        modelMapper.map(clienteRequest, cliente);
    }
}
