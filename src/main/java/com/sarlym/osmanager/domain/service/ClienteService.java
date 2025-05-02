package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.mapper.ClienteMapper;
import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.exception.ClienteException;
import com.sarlym.osmanager.domain.exception.EmailJaExistenteException;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
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
                () -> new ClienteException("Cliente não pode ser deletado pois não foi encontrado"));
    }

    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.paraModel(clienteRequest);
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaExistenteException("Email " + cliente.getEmail() + " já cadastrado no sistema");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente alterarCliente(Long id, ClienteRequest clienteRequest) {
        Cliente clienteAntigo = buscarClienteOuErro(id);
        Cliente cliente = clienteMapper.paraModel(clienteRequest);
        cliente.setId(clienteAntigo.getId());
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = buscarClienteOuErro(id);
        clienteRepository.delete(cliente);
        clienteRepository.flush();
    }
}
