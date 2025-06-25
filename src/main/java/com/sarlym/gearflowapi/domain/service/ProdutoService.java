package com.sarlym.gearflowapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.gearflowapi.api.dto.mapper.ProdutoMapper;
import com.sarlym.gearflowapi.api.dto.request.ProdutoRequest;
import com.sarlym.gearflowapi.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.gearflowapi.domain.model.Produto;
import com.sarlym.gearflowapi.domain.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public Produto buscaProdutoOuErro(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado no estoque"));
    }

    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    public Produto salvar(ProdutoRequest request) {
        Produto produto = produtoMapper.requestParaModelo(request);
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long IdProduto, ProdutoRequest request) {
        Produto produto = buscaProdutoOuErro(IdProduto);
        produtoMapper.copiaParaNovo(request, produto);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(long id) {
        Produto produto = buscaProdutoOuErro(id);
        produtoRepository.delete(produto);
        produtoRepository.flush();
    }

}
