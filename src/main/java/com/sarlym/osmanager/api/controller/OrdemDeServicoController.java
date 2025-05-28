package com.sarlym.osmanager.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.mapper.OrdemServicoMapper;
import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.api.dto.response.OrdemServicoDTO;
import com.sarlym.osmanager.api.dto.response.OrdemServicoResumo;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.service.OrdemServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/ordens-servico", produces = {"application/json"})
@Tag(name = "Ordens-Servico", description = "Operações com as ordens de serviço")
public class OrdemDeServicoController {

    private OrdemServicoService ordemServicoService;
    private OrdemServicoMapper ordemServicoMapper;

    public OrdemDeServicoController(OrdemServicoService ordemServicoService, OrdemServicoMapper ordemServicoMapper) {
        this.ordemServicoService = ordemServicoService;
        this.ordemServicoMapper = ordemServicoMapper;
    }

    @Operation(summary = "Busca por ordem de serviço", description = "Busca uma OS no sistema utilizando o ID como parametro.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de Ordem de Serviço"),
    })
    @GetMapping("/{id}")
    public OrdemServicoDTO buscamosOrdemServico(
            @Parameter(name = "id", description = "ID único da ordem de serviço", required = true, example = "1") @PathVariable(value = "id") Long id) {
        return ordemServicoMapper.modeloParaDTO(ordemServicoService.buscaOrdemServicoOuErro(id));
    }

    @Operation(summary = "Busca por ordem de serviço", description = "Busca uma OS no sistema utilizando vários parametros.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de Ordem de Serviço"),
    })
    @GetMapping
    public ResponseEntity<List<OrdemServicoResumo>> buscaFiltrada(
            @RequestParam(required = false) String numeroOs,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long veiculoId,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFim) {
        List<OrdemServico> ordens = ordemServicoService.buscaComFiltros(numeroOs, status, veiculoId, dataInicio,
                dataFim);
        return ResponseEntity.ok(ordemServicoMapper.modeloListaParaListaDTOResumo(ordens));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastra ordem de serviço", description = "Cadastra uma nova ordem de serviço ao passar os valores veiculoID, mecanicoID e descrição do problema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar pesquisa")
    })
    @PostMapping
    public OrdemServicoDTO criaOrdemDeServico(@RequestBody(required = true) OrdemServicoRequest ordemServicoRequest) {
        return ordemServicoService.salvar(ordemServicoRequest);
    }

    @Operation(summary = "Alterar o serviço", description = "Altera um registro de uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço, mecanico ou veiculo não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar atualização")
    })
    @PutMapping("/{id}")
    public OrdemServicoDTO alteraOrdemServico(
            @Parameter(name = "id", description = "ID único da ordem de serviço", required = true, example = "1") @PathVariable(name = "id") Long id,
            @RequestBody(required = true) OrdemServicoRequest ordemServicoRequest) {
        return ordemServicoService.alterarOrdemServico(id, ordemServicoRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletaOrdemServico(
                 @Parameter(name = "id", description = "ID único da OS", required = true, example = "1") @PathVariable(name = "id") Long id) {
        ordemServicoService.deletaOrdemServico(id);
    }
}
