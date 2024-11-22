package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.ClienteDTO;
import com.sarlym.osmanager.api.dto.dtoconverter.ClienteDTOConverter;
import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.api.dto.response.ClienteResponse;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteResponse clienteResponse;
    private final ClienteDTOConverter clienteDTOConverter;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteResponse clienteResponse, ClienteDTOConverter clienteDTOConverter) {
        this.clienteRepository = clienteRepository;
        this.clienteResponse = clienteResponse;
        this.clienteDTOConverter = clienteDTOConverter;
    }

    public List<ClienteDTO> clientes() {
        return clienteDTOConverter.paraDTOLista(clienteRepository.findAll());
    }

    public ClienteDTO cadastrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteResponse.paraModel(clienteRequest);
        return clienteDTOConverter.paraDTO(clienteRepository.save(cliente));
    }
}
