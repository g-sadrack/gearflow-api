package com.sarlym.osmanager.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.osmanager.api.dto.OrdemServicoDTO;
import com.sarlym.osmanager.api.dto.dtoconverter.OrdemServicoConverter;
import com.sarlym.osmanager.domain.service.OrdemServicoService;

@RestController
@RequestMapping(value = "api/ordens-servico")
public class OrdemDeServicoController {

    private OrdemServicoService ordemServicoService;
    private OrdemServicoConverter ordemServicoConverter;

    public OrdemDeServicoController(OrdemServicoService ordemServicoService,
            OrdemServicoConverter ordemServicoConverter) {
        this.ordemServicoService = ordemServicoService;
        this.ordemServicoConverter = ordemServicoConverter;
    }

    @GetMapping("/{id}")
    public OrdemServicoDTO buOrdemServico(@PathVariable Long id) {
        return ordemServicoConverter.paraDto(ordemServicoService.buscaOrdemServicoOuErro(id));
    }
}
