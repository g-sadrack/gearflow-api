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

import com.sarlym.osmanager.api.dto.ServicoDTO;
import com.sarlym.osmanager.api.dto.dtoconverter.ServicoConverter;
import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.domain.service.ServicoService;

@RequestMapping("/servicos")
@RestController
public class ServicoController {

    private final ServicoService servicoService;
    private final ServicoConverter servicoConverter;
    
    public ServicoController(ServicoService servicoService, ServicoConverter servicoConverter) {
        this.servicoService = servicoService;
        this.servicoConverter = servicoConverter;
    }

    @GetMapping
    public List<ServicoDTO> listarServicos() {
        return servicoConverter.paraDTOLista(servicoService.listarServicos());
    }

    @GetMapping("/{id}")
    public ServicoDTO buscarServico(@PathVariable Long id) {
        return servicoConverter.paraDTO(servicoService.buscarServicoOuErro(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoDTO cadastrarServico(@RequestBody ServicoRequest servicoRequest) {
        return servicoConverter.paraDTO(servicoService.cadastrarServico(servicoRequest));
    }

    @PutMapping("/{id}")
    public ServicoDTO alterarServico(@PathVariable Long id, @RequestBody ServicoRequest servicoRequest) {
        return servicoConverter.paraDTO(servicoService.alterarServico(id, servicoRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirServico(@PathVariable Long id) {
        servicoService.excluirServico(id);
    }
}
