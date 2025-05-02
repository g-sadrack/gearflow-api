package com.sarlym.osmanager.api.dto.mapper;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.api.dto.response.ClienteDTO;
import com.sarlym.osmanager.domain.model.Cliente;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Cliente paraModel(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public ClienteDTO paraDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> paraDTOLista(List<Cliente> clientes) {
        return clientes.stream().map(this::paraDTO).toList();
    }

}
