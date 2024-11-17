package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }
}
