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

import com.sarlym.osmanager.api.dto.mapper.ServicoMapper;
import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.api.dto.response.ServicoDTO;
import com.sarlym.osmanager.domain.service.ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/servicos", produces = { "application/json" })
@Tag(name = "Serviços", description = "Operações com serviços")
public class ServicoController {

    private final ServicoService servicoService;
    private final ServicoMapper servicoMapper;

    public ServicoController(ServicoService servicoService, ServicoMapper servicoMapper) {
        this.servicoService = servicoService;
        this.servicoMapper = servicoMapper;
    }

    @Operation(summary = "Lista os serviços", description = "Lista todos os serviços prestados pela mecanica", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
            @ApiResponse(responseCode = "505", description = "Erro ao listar serviços")
    })
    @GetMapping
    public List<ServicoDTO> listarServicos() {
        return servicoMapper.modelLitaParaDTOLista(servicoService.listarServicos());
    }

    @Operation(summary = "Realiza busca de serviço por ID", description = "Busca um serviço no sistema utilizando o ID como parametro.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de cliente"),
    })
    @GetMapping("/{id}")
    public ServicoDTO buscarServico(@PathVariable(name = "id") Long id) {
        return servicoMapper.modelParaDTO(servicoService.buscarServicoOuErro(id));
    }

    @Operation(summary = "Cadastra ordem de serviço", description = "Cadastra uma nova ordem de serviço ao passar os valores veiculoID, mecanicoID e descrição do problema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar pesquisa")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoDTO cadastrarServico(@RequestBody(required = true) ServicoRequest servicoRequest) {
        return servicoMapper.modelParaDTO(servicoService.cadastrarServico(servicoRequest));
    }

    @Operation(summary = "Alterar o serviço", description = "Altera um registro de um serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de Serviço não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar atualização")
    })
    @PutMapping("/{id}")
    public ServicoDTO alterarServico(@PathVariable Long id,
            @RequestBody(required = true) ServicoRequest servicoRequest) {
        return servicoMapper.modelParaDTO(servicoService.alterarServico(id, servicoRequest));
    }

    @Operation(summary = "Deletar serviço", description = "Deleta um registro de serviço", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de serviço não encontrado"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirServico(@PathVariable Long id) {
        servicoService.excluirServico(id);
    }
}
