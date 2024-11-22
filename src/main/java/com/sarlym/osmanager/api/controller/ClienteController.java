package com.sarlym.osmanager.api.controller;

import com.sarlym.osmanager.api.dto.ClienteDTO;
import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable Long id){
        return clienteService.buscarClienteOuErro(id);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes(){
        return clienteService.clientes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO cadastrarCliente(@RequestBody ClienteRequest clienteRequest){
        return clienteService.cadastrarCliente(clienteRequest);
    }

    @PutMapping("/{id}")
    public ClienteDTO alterarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
        return clienteService.alterarCliente(id,clienteRequest);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }

}
