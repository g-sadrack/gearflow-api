package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.mapper.ClienteMapper;
import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.exception.ClienteException;
import com.sarlym.osmanager.domain.exception.EmailJaExistenteException;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Cliente buscarClienteOuErro(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("Cliente não pode ser encontrado"));
    }

    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente cadastrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.requestParaModel(clienteRequest);
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaExistenteException("Email " + cliente.getEmail() + " já cadastrado no sistema");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente alterarCliente(Long id, ClienteRequest clienteRequest) {
        Cliente cliente = buscarClienteOuErro(id);
        clienteMapper.copiaParaNovo(clienteRequest, cliente);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletarCliente(Long id) {
        Cliente cliente = buscarClienteOuErro(id);
        clienteRepository.delete(cliente);
        clienteRepository.flush();
    }
}
