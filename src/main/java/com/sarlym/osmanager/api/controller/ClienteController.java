package com.sarlym.osmanager.api.controller;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.clientes();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody ClienteRequest clienteRequest){
        return clienteService.cadastrarCliente(clienteRequest);
    }

}
