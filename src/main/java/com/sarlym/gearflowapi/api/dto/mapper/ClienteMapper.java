package com.sarlym.gearflowapi.api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.gearflowapi.api.dto.request.ClienteRequest;
import com.sarlym.gearflowapi.api.dto.response.ClienteDTO;
import com.sarlym.gearflowapi.domain.model.Cliente;

@Component
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente requestParaModel(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public ClienteDTO modelParaDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente dTOParaModel(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    public List<ClienteDTO> modelListaParaDTOLista(List<Cliente> clientes) {
        return clientes.stream().map(this::modelParaDTO).toList();
    }

    public void copiaParaNovo(ClienteRequest clienteRequest, Cliente cliente) {
        modelMapper.map(clienteRequest, cliente);
    }

}
