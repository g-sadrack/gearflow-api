package com.sarlym.osmanager.api.controller;

import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
import com.sarlym.osmanager.api.dto.response.VeiculoDTO;
import com.sarlym.osmanager.domain.service.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/veiculos", produces = "application/json")
@Tag(name = "Veiculo", description = "Operações relacionadas aos veículos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(summary = "Lista todos os veiculos", description = "Faz a listagem de todos os veiuclos cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os veiculos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar veiculos")
    })
    @GetMapping
    public List<VeiculoDTO> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @Operation(summary = "Realiza busca de veiculos por ID", description = "Busca um veiculo no sistema utilizando o ID como parametro.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de cliente"),
    })
    @GetMapping("/{id}")
    public VeiculoDTO buscarVeiculo(@PathVariable(name = "id") Long id) {
        return veiculoService.buscarVeiculoOuErro(id);
    }

    @Operation(summary = "Cadastra ordem de veiculo", description = "Cadastra uma nova ordem de serviço ao passar os valores placa, marca, modelo, ano, cor e quilometragem veiculo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar pesquisa")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDTO cadastrarVeiculo(@RequestBody(required = true) VeiculoRequest veiculoRequest) {
        return veiculoService.cadastrarVeiculo(veiculoRequest);
    }

    @Operation(summary = "Alterar o veiculo", description = "Altera um registro de um veiculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de veiculo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar atualização")
    })
    @PutMapping("/{id}")
    public VeiculoDTO alterarVeiculo(@PathVariable(name = "id") Long id,
            @RequestBody(required = true) VeiculoRequest veiculoRequest) {
        return veiculoService.alterarVeiculo(id, veiculoRequest);
    }

    @Operation(summary = "Deletar veiculo", description = "Deleta um registro de veiculo", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de serviço não encontrado"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
    }
}
