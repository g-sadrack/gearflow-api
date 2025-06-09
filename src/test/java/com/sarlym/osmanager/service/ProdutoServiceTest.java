package com.sarlym.osmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sarlym.osmanager.api.dto.mapper.ProdutoMapper;
import com.sarlym.osmanager.api.dto.request.ProdutoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Produto;
import com.sarlym.osmanager.domain.repositories.ProdutoRepository;
import com.sarlym.osmanager.domain.service.ProdutoService;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoMapper produtoMapper;
    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private ProdutoRequest request;

    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "A001", "Radiador", new BigDecimal(120.00), LocalDateTime.now(),
                LocalDateTime.now());
        request = new ProdutoRequest(2L,"002", "Bomba de água");
    }

    @Test
    void deve_RetornarProduto_quandoExistir() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto produto = produtoService.buscaProdutoOuErro(1L);
        assertNotNull(produto);
        assertEquals(1l, produto.getId());
        verify(produtoRepository).findById(1L);
    }

    @Test
    void deve_RetornarExcessao_quandoNaoExistir() {
        // Arrange
        when(produtoRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
                () -> produtoService.buscaProdutoOuErro(2L));

        // Assert
        assertEquals("Produto não encontrado no estoque", exception.getMessage());
        verify(produtoRepository).findById(2L);
    }

    @Test
    void deve_RetornarListaDeProdutos() {
        // Arrange
        List<Produto> produtos = List.of(produto);
        when(produtoRepository.findAll()).thenReturn(produtos);

        // Act
        List<Produto> listaProdutos = produtoRepository.findAll();

        // Assert
        assertNotNull(listaProdutos);
        assertEquals(1, listaProdutos.size());
        verify(produtoRepository).findAll();
    }

    @Test
    void deve_SalvarProduto_quandoDadosValidos() {
        // Arrange
        ProdutoRequest request = mock(ProdutoRequest.class);
        Produto p1 = new Produto();
        when(produtoMapper.requestParaModelo(request)).thenReturn(p1);
        when(produtoRepository.save(any())).thenReturn(p1);

        // Act
        Produto produto = produtoService.salvar(request);

        // Assert
        assertNotNull(produto);
        verify(produtoRepository).save(produto);
    }

    @Test
    void deve_AtualizarProduto_quandoIdExistir() {
        // Arrange
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
        doNothing().when(produtoMapper).copiaParaNovo(request, produto);
        when(produtoRepository.save(produto)).thenReturn(produto);

        // Act
        Produto p = produtoService.atualizarProduto(1L, request);

        // Assert
        assertNotNull(p);
        verify(produtoMapper).copiaParaNovo(request, p);
        verify(produtoRepository).save(p);
    }

    @Test
    void deve_RemoverProduto_quandoExiste() {
        // Arrange
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).delete(produto);
        doNothing().when(produtoRepository).flush();

        // Act
        produtoService.deletarProduto(1L);

        // Assert
        verify(produtoRepository).delete(produto);
        verify(produtoRepository).flush();
    }

}
