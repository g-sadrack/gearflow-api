package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.api.dto.response.ClienteResponse;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteResponse clienteResponse;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteResponse clienteResponse) {
        this.clienteRepository = clienteRepository;
        this.clienteResponse = clienteResponse;
    }

    public Cliente buscarClienteOuErro(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não com ID não encontrado"));
    }

    public List<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteResponse.paraModel(clienteRequest);
        return clienteRepository.save(cliente);
    }

    public Cliente alterarCliente(Long id, ClienteRequest clienteRequest) {
       Cliente clienteAntigo = buscarClienteOuErro(id);
       Cliente cliente = clienteResponse.paraModel(clienteRequest);
       cliente.setId(clienteAntigo.getId());
       return clienteRepository.save(cliente);
    }


    public void deletarCliente(Long id) {
        try {
            Cliente cliente = buscarClienteOuErro(id);
            clienteRepository.delete(cliente);
        } catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Item não pode ser deletado");
        }
    }
}
