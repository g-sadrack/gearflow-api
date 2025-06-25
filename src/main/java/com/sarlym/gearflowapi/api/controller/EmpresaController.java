package com.sarlym.gearflowapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.gearflowapi.api.dto.mapper.EmpresaMapper;
import com.sarlym.gearflowapi.api.dto.request.EmpresaRequest;
import com.sarlym.gearflowapi.api.dto.response.EmpresaResumo;
import com.sarlym.gearflowapi.domain.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/empresas", produces = "application/json")
@Tag(name = "Empresas", description = "Operações relacionadas a empresas")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;

    public EmpresaController(EmpresaService empresaService, EmpresaMapper empresaMapper) {
        this.empresaService = empresaService;
        this.empresaMapper = empresaMapper;
    }

    @Operation(summary = "Realiza busca de empresa", description = "Busca uma empresa cadastrada no sistema, mostrando o nome, e-mail e telefone.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa nao encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de empresa"),
    })
    @GetMapping("/{id}")
    public EmpresaResumo buscaEmpresa(
            @Parameter(name = "id", description = "ID único da empresa", required = true, example = "1") @PathVariable(name = "id") Long id) {
        return empresaMapper.modelParaDTO(empresaService.buscarEmpresaOuErro(id));
    }

    @Operation(summary = "Realiza busca de empresas", description = "Busca todas as empresas cadastradas no sistema, mostrando o nome, e-mail e telefone.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de empresas"),
    })
    @GetMapping
    public List<EmpresaResumo> listarEmpresas() {
        return empresaMapper.modelListaParaDTOLista(empresaService.listarEmpresas());
    }

    @Operation(summary = "Realiza cadastro de empresa", description = "Cadastra no sistema uma empresa ao passar o nome, e-mail e telefone.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a cadastro de empresa, e-mail ja cadastrado."),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaResumo salvar(@RequestBody(required = true) EmpresaRequest empresaRequest) {
        return empresaMapper.modelParaDTO(empresaService.salvar(empresaRequest));
    }

    @Operation(summary = "Realiza atualização de empresa", description = "Atualiza no sistema uma empresa ao passar o nome, e-mail e telefone.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizacao realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a atualizacao de empresa, e-mail ja cadastrado."),
    })
    @PutMapping("/{id}")
    public EmpresaResumo atualizar(@PathVariable(name = "id") Long id,
            @RequestBody(required = true) EmpresaRequest empresaRequest) {
        return empresaMapper.modelParaDTO(empresaService.atualizar(id, empresaRequest));
    }

}
