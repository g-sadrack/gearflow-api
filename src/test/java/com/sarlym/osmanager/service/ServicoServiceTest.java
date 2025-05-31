package com.sarlym.osmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sarlym.osmanager.api.dto.mapper.ServicoMapper;
import com.sarlym.osmanager.api.dto.request.ServicoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Servico;
import com.sarlym.osmanager.domain.repository.ServicoRepository;
import com.sarlym.osmanager.domain.service.ServicoService;

@ExtendWith(MockitoExtension.class)
class ServicoServiceTest {

    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private ServicoMapper servicoMapper;

    @InjectMocks
    private ServicoService servicoService;

    private Servico servico;
    private ServicoRequest servicoRequest;

    @BeforeEach
    void setUp() {
        servico = new Servico();
        servico.setId(1L);
        servico.setDescricao("Troca de óleo");

        servicoRequest = new ServicoRequest();
        servicoRequest.setDescricao("Troca de óleo");
    }

    @Test
    void deve_RetornarServico_quandoExistir() {
        // Arrange
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        // Act
        Servico resultado = servicoService.buscarServicoOuErro(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Troca de óleo", resultado.getDescricao());
        verify(servicoRepository).findById(1L);
    }

    @Test
    void deve_LancarExcecao_quandoNaoEncontrado() {
        // Arrange
        when(servicoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
                () -> servicoService.buscarServicoOuErro(1L));

        assertEquals("Serviço não encontrado", exception.getMessage());
        verify(servicoRepository).findById(1L);
    }

    @Test
    void deve_RetornarListaDeServicos() {
        // Arrange
        List<Servico> lista = List.of(servico);
        when(servicoRepository.findAll()).thenReturn(lista);

        // Act
        List<Servico> resultado = servicoService.listarServicos();

        // Assert
        assertEquals(1, resultado.size());
        verify(servicoRepository).findAll();
    }

    @Test
    void deve_SalvarServico_quandoRequestValido() {
        // Arrange
        when(servicoMapper.requestParaModel(servicoRequest)).thenReturn(servico);
        when(servicoRepository.save(servico)).thenReturn(servico);

        // Act
        Servico resultado = servicoService.cadastrarServico(servicoRequest);

        // Assert
        assertNotNull(resultado);
        verify(servicoRepository).save(servico);
    }

    @Test
    void deve_AtualizarServico_quandoIdExistir() {
        // Arrange
        Servico servicoAtualizado = new Servico();
        servicoAtualizado.setId(1L);
        servicoAtualizado.setDescricao("Alinhamento");

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(servicoMapper.requestParaModel(servicoRequest)).thenReturn(servicoAtualizado);
        when(servicoRepository.save(servicoAtualizado)).thenReturn(servicoAtualizado);

        // Act
        Servico resultado = servicoService.alterarServico(1L, servicoRequest);

        // Assert
        assertEquals("Alinhamento", resultado.getDescricao());
        assertEquals(1L, resultado.getId());
        verify(servicoRepository).save(servicoAtualizado);
    }

    @Test
    void deve_RemoverServico_QuandoIdExistir() {
        // Arrange
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        doNothing().when(servicoRepository).delete(servico);
        doNothing().when(servicoRepository).flush();

        // Act
        servicoService.excluirServico(1L);

        // Assert
        verify(servicoRepository).delete(servico);
        verify(servicoRepository).flush();
    }
}