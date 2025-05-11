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

    public Cliente RequestParaModel(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public ClienteDTO ModelParaDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente DTOParaModel(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    public List<ClienteDTO> ModelListaParaDTOLista(List<Cliente> clientes) {
        return clientes.stream().map(this::ModelParaDTO).toList();
    }

}
