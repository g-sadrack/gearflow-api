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

import com.sarlym.osmanager.api.dto.mapper.ProdutoMapper;
import com.sarlym.osmanager.api.dto.request.ProdutoRequest;
import com.sarlym.osmanager.api.dto.response.ProdutoDTO;
import com.sarlym.osmanager.domain.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/produtos", produces = { "application/json" })
@Tag(name = "Produtos", description = "Operações relacionadas aos produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Operation(summary = "Busca um produto no sistema", description = "Busca um produto no sistema utilizando o ID como parametro.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de produto"),
    })
    @GetMapping("/{id}")
    public ProdutoDTO buscaProduto(@PathVariable(name = "id") Long id) {
        return produtoMapper.modelParaDTO(produtoService.buscaProdutoOuErro(id));
    }

    @Operation(summary = "Busca todos os produtos no sistema", description = "Busca todos os produtos no sistema.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produtos nao encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de produtos"),
    })
    @GetMapping
    public List<ProdutoDTO> listarProduto() {
        return produtoMapper.modeloParaDTOLista(produtoService.listaProdutos());
    }

    @Operation(summary = "Cria um produto no sistema", description = "Cria um produto no sistema.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar produto"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO cadastrarProduto(@RequestBody(required = true) ProdutoRequest produtoRequest) {
        return produtoMapper.modelParaDTO(produtoService.salvar(produtoRequest));
    }

    @Operation(summary = "Altera um produto no sistema", description = "Altera um produto no sistema.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar atualização"),
    })
    @PutMapping("/{id}")
    public ProdutoDTO alterarProduto(@PathVariable(name = "id") Long id,
            @RequestBody(required = true) ProdutoRequest produtoRequest) {
        return produtoMapper.modelParaDTO(produtoService.atualizarProduto(id, produtoRequest));
    }

    @Operation(summary = "Deleta um produto no sistema", description = "Deleta um produto no sistema.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID de produto nao encontrado"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable(name = "id") Long id) {
        produtoService.deletarProduto(id);
    }

}
