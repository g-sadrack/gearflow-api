package com.sarlym.osmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sarlym.osmanager.api.dto.mapper.MecanicoMapper;
import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Empresa;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.repositories.MecanicoRepository;
import com.sarlym.osmanager.domain.service.MecanicoService;

@ExtendWith(MockitoExtension.class)
class MecanicoServiceTest {

    @Mock
    private MecanicoRepository mecanicoRepository;

    @Mock
    private MecanicoMapper mecanicoMapper;

    @InjectMocks
    private MecanicoService mecanicoService;

    private Mecanico mecanico;
    private MecanicoRequest mecanicoRequest;

    @BeforeEach
    void setUp() {
        mecanico = new Mecanico(1L, "Carlos", "Motor", "001", Boolean.TRUE, LocalDateTime.now(), LocalDateTime.now(), new Empresa());
       
        mecanicoRequest = new MecanicoRequest("Carlos", "Motor", "001", Boolean.TRUE);
    
    }

    @Test
    void deve_RetornarMecanico_quandoExistir() {
        // Arrange
        when(mecanicoRepository.findById(1L)).thenReturn(Optional.of(mecanico));

        // Act
        Mecanico resultado = mecanicoService.buscarMecanicoOuErro(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNome());
        verify(mecanicoRepository).findById(1L);
    }

    @Test
    void deve_LancarExcecao_quandoNaoExiste() {
        // Arrange
        when(mecanicoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
                () -> mecanicoService.buscarMecanicoOuErro(1L));

        assertTrue(exception.getMessage().contains("Mecanico com id 1, nao encontrado"));
        verify(mecanicoRepository).findById(1L);
    }

    @Test
    void deve_RetornarListaDeMecanicos() {
        // Arrange
        List<Mecanico> lista = List.of(mecanico);
        when(mecanicoRepository.findAll()).thenReturn(lista);

        // Act
        List<Mecanico> resultado = mecanicoService.listarMecanicos();

        // Assert
        assertEquals(1, resultado.size());
        verify(mecanicoRepository).findAll();
    }

    @Test
    void deve_SalvarMecanico_quandoRequestValido() {
        // Arrange
        when(mecanicoMapper.requestParaModel(mecanicoRequest)).thenReturn(mecanico);
        when(mecanicoRepository.save(mecanico)).thenReturn(mecanico);

        // Act
        Mecanico resultado = mecanicoService.cadastrarMecanico(mecanicoRequest);

        // Assert
        assertNotNull(resultado);
        verify(mecanicoRepository).save(mecanico);
    }

    @Test
    void deve_AtualizarMecanico_quandoIdExistir() {
        // Arrange
        Mecanico mecanicoAtualizado = new Mecanico();
        mecanicoAtualizado.setId(1L);
        mecanicoAtualizado.setNome("Carlos Atualizado");

        when(mecanicoRepository.findById(1L)).thenReturn(Optional.of(mecanico));
        when(mecanicoMapper.requestParaModel(mecanicoRequest)).thenReturn(mecanicoAtualizado);
        when(mecanicoRepository.save(mecanicoAtualizado)).thenReturn(mecanicoAtualizado);

        // Act
        Mecanico resultado = mecanicoService.atualizarMecanico(1L, mecanicoRequest);

        // Assert
        assertEquals("Carlos Atualizado", resultado.getNome());
        assertEquals(1L, resultado.getId());
        verify(mecanicoRepository).save(mecanicoAtualizado);
    }

    @Test
    void deve_RemoverMecanico_quandoIdExistir() {
        // Arrange
        when(mecanicoRepository.findById(1L)).thenReturn(Optional.of(mecanico));
        doNothing().when(mecanicoRepository).delete(mecanico);
        doNothing().when(mecanicoRepository).flush();

        // Act
        mecanicoService.excluirMecanico(1L);

        // Assert
        verify(mecanicoRepository).delete(mecanico);
        verify(mecanicoRepository).flush();
    }
}
