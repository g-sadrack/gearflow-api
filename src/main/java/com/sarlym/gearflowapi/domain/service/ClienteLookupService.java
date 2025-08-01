package com.sarlym.gearflowapi.domain.service;

import org.springframework.stereotype.Service;

import com.sarlym.gearflowapi.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.gearflowapi.domain.model.Cliente;
import com.sarlym.gearflowapi.domain.repositories.ClienteRepository;

@Service
public class ClienteLookupService {

    private final ClienteRepository clienteRepository;

    public ClienteLookupService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente nao encontrado"));
    }
}
