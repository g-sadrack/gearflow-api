package com.sarlym.osmanager.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.mapper.OrdemServicoMapper;
import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.api.dto.response.OrdemServicoDTO;
import com.sarlym.osmanager.api.dto.response.OrdemServicoResumo;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.service.OrdemServicoService;

@RestController
@RequestMapping(value = "api/ordens-servico")
public class OrdemDeServicoController {

    private OrdemServicoService ordemServicoService;
    private OrdemServicoMapper ordemServicoMapper;

    public OrdemDeServicoController(OrdemServicoService ordemServicoService,
            OrdemServicoMapper ordemServicoMapper) {
        this.ordemServicoService = ordemServicoService;
        this.ordemServicoMapper = ordemServicoMapper;
    }

    @GetMapping("/{id}")
    public OrdemServicoDTO buOrdemServico(@PathVariable Long id) {
        return ordemServicoMapper.paraDTO(ordemServicoService.buscaOrdemServicoOuErro(id));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResumo>> buscaFiltrada(
            @RequestParam(required = false) String numeroOs,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFim) {
        List<OrdemServico> ordens = ordemServicoService.buscaComFiltros(numeroOs, status, veiculoId, dataInicio,
                dataFim);
        return ResponseEntity.ok(ordemServicoMapper.paraListaDTOResumo(ordens));
    }

    @PostMapping
    public OrdemServicoDTO criaOrdemDeServico (@RequestBody OrdemServicoRequest ordemServicoRequest){
        return ordemServicoMapper.paraDTO(ordemServicoService.salvar(ordemServicoRequest));
    }
}
