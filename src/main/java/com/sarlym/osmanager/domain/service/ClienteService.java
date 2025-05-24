package com.sarlym.osmanager.domain.service;

import com.sarlym.osmanager.api.dto.mapper.ClienteMapper;
import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.api.dto.response.ClienteDTO;
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

    public ClienteDTO buscarClienteOuErro(Long id) {
        return clienteMapper.modelParaDTO(clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("Cliente não pode ser deletado pois não foi encontrado")));
    }

    public List<ClienteDTO> clientes() {
        return clienteMapper.modelListaParaDTOLista(clienteRepository.findAll());
    }

    public ClienteDTO cadastrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.requestParaModel(clienteRequest);
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaExistenteException("Email " + cliente.getEmail() + " já cadastrado no sistema");
        }
        return clienteMapper.modelParaDTO(clienteRepository.save(cliente));
    }

    public ClienteDTO alterarCliente(Long id, ClienteRequest clienteRequest) {
        Cliente clienteAntigo = clienteMapper.dTOParaModel(buscarClienteOuErro(id));
        Cliente cliente = clienteMapper.requestParaModel(clienteRequest);
        cliente.setId(clienteAntigo.getId());
        return clienteMapper.modelParaDTO(clienteRepository.save(cliente));
    }

    public void deletarCliente(Long id) {
        Cliente cliente = clienteMapper.dTOParaModel(buscarClienteOuErro(id));
        clienteRepository.delete(cliente);
        clienteRepository.flush();
    }
}
