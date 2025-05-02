package com.sarlym.osmanager.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.OrdemServicoDTO;
import com.sarlym.osmanager.api.dto.dtoconverter.OrdemServicoConverter;
import com.sarlym.osmanager.domain.model.OrdemServico;
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

    @GetMapping
    public ResponseEntity<List<OrdemServicoDTO>> buscaFiltrada(
            @RequestParam(required = false) String numeroOs,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFim) {
        List<OrdemServico> ordens = ordemServicoService.buscaComFiltros(numeroOs, status, veiculoId, dataInicio, dataFim);
        return ResponseEntity.ok(ordemServicoConverter.paraListaDTO(ordens));
    }
}
