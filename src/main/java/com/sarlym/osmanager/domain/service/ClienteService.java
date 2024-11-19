package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.api.responseconverter.ClienteConverter;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteConverter clienteConverter;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteConverter clienteConverter) {
        this.clienteRepository = clienteRepository;
        this.clienteConverter = clienteConverter;
    }

    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteConverter.paraModelo(clienteRequest);
        return clienteRepository.save(cliente);
    }
}
