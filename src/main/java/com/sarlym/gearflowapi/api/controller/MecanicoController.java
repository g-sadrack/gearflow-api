package com.sarlym.gearflowapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sarlym.gearflowapi.api.dto.mapper.MecanicoMapper;
import com.sarlym.gearflowapi.api.dto.request.MecanicoRequest;
import com.sarlym.gearflowapi.api.dto.response.MecanicoDTO;
import com.sarlym.gearflowapi.domain.service.MecanicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/mecanicos", produces = { "application/json" })
@Tag(name = "Mecanicos", description = "Operações relacionadas aos mecanicos")
public class MecanicoController {

    private final MecanicoService mecanicoService;
    private final MecanicoMapper mecanicoMapper;

    public MecanicoController(MecanicoService mecanicoService, MecanicoMapper mecanicoMapper) {
        this.mecanicoService = mecanicoService;
        this.mecanicoMapper = mecanicoMapper;
    }

    @Operation(summary = "Realiza busca de mecanico por ID", description = "Busca um mecanico no sistema utilizando o ID como parametro.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de mecanico"),
    })
    @GetMapping("/{id}")
    public MecanicoDTO buscarMecanico(
            @Parameter(name = "id", description = "ID único do mecanico", required = true, example = "1") @PathVariable(value = "id") Long id) {
        return mecanicoMapper.modelParaDTO(mecanicoService.buscarMecanicoOuErro(id));
    }

    @Operation(summary = "Realiza listagem de mecanicos", description = "Lista os mecanicos cadastrados no sistema, mostrando o nome, especialidade, matricula e se está ativo.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de mecanicos"),
    })
    @GetMapping
    public List<MecanicoDTO> listarMecanicos() {
        return mecanicoMapper.modelListaParaDTOLista(mecanicoService.listarMecanicos());
    }

    @Operation(summary = "Realiza cadastro de mecanico", description = "Cadastra no sistema um mecanico ao passar o nome, especialidade, matricula e se está ativo.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a cadastro de mecanico, e-mail já cadastrado."),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MecanicoDTO cadastrarMecanico(@RequestBody(required = true) MecanicoRequest mecanicoRequest) {
        return mecanicoMapper.modelParaDTO(mecanicoService.cadastrarMecanico(mecanicoRequest));
    }

    @Operation(summary = "Alteração de mecanico", description = "Altera uma ou mais informações do mecanico", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de do mecanico não encontrado"),

    })
    @PutMapping("/{id}")
    public MecanicoDTO alterarMecanico(
            @Parameter(name = "id", description = "ID único do mecanico", required = true, example = "1") @PathVariable(value = "id") Long id,
            @RequestBody(required = true) MecanicoRequest MecanicoRequest) {
        return mecanicoMapper.modelParaDTO(mecanicoService.atualizarMecanico(id, MecanicoRequest));
    }

    @Operation(summary = "Deletar de mecanico", description = "Deleta um registro de mecanico", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de mecanico não encontrado"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMecanico(@PathVariable(value = "id") Long id) {
        mecanicoService.excluirMecanico(id);
    }
}
