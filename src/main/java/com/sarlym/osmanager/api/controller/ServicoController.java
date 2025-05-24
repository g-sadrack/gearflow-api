package com.sarlym.osmanager.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.api.dto.response.ServicoDTO;
import com.sarlym.osmanager.domain.service.ServicoService;

@RequestMapping("/servicos")
@RestController
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<ServicoDTO> listarServicos() {
        return servicoService.listarServicos();
    }

    @GetMapping("/{id}")
    public ServicoDTO buscarServico(@PathVariable Long id) {
        return servicoService.buscarServicoOuErro(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoDTO cadastrarServico(@RequestBody ServicoRequest servicoRequest) {
        return servicoService.cadastrarServico(servicoRequest);
    }

    @PutMapping("/{id}")
    public ServicoDTO alterarServico(@PathVariable Long id, @RequestBody ServicoRequest servicoRequest) {
        return servicoService.alterarServico(id, servicoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirServico(@PathVariable Long id) {
        servicoService.excluirServico(id);
    }
}
