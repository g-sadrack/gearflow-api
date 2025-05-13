package com.sarlym.osmanager.service;

import com.sarlym.osmanager.api.dto.mapper.MecanicoMapper;
import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.api.dto.response.MecanicoDTO;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.repository.MecanicoRepository;
import com.sarlym.osmanager.domain.service.MecanicoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MecanicoServiceTest {

    @Mock
    private Mecanico mecanico;
    @Mock
    private Mecanico mecanicoAntigo;
    @Mock
    private Mecanico mecanicoAtualizado;
    @Mock
    private MecanicoMapper mecanicoResponse;
    @Mock
    private MecanicoRepository mecanicoRepository;
    @Mock
    private MecanicoRequest mecanicoRequest;
    @Mock
    private List<Mecanico> mecanicos;

    private MecanicoService mecanicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mecanicoService = new MecanicoService(mecanicoRepository, mecanicoResponse);
        startMecanico();
    }

    @Test
    void quandoBuscarMecanicoPorIdRetornarApenasUm() {
        when(mecanicoRepository.findById(anyLong())).thenReturn(Optional.of(mecanico));

        MecanicoDTO resultado = mecanicoService.buscarMecanicoOuErro(1L);

        assertNotNull(resultado);
        assertEquals(mecanico.getId(), resultado.getId());
        verify(mecanicoRepository, times(1)).findById(mecanico.getId());
    }

    @Test
    void quandoBuscarMecanicoInexistenteRetornarErro() {
        when(mecanicoRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            mecanicoService.buscarMecanicoOuErro(2L);
        });

        assertEquals("Mecanico com id 2, nao encontrado", exception.getMessage());
        verify(mecanicoRepository, times(1)).findById(2L);
    }

    @Test
    void quandoListarMecanicosEntaoRetornarSucesso() {
        when(mecanicoRepository.findAll()).thenReturn(mecanicos);

        List<MecanicoDTO> resultado = mecanicoService.listarMecanicos();

        assertNotNull(resultado);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremMecanicos() {
        when(mecanicoRepository.findAll()).thenReturn(List.of());

        List<MecanicoDTO> resultado = mecanicoService.listarMecanicos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(mecanicoRepository, times(1)).findAll();
    }

    @Test
    void quandoCadastrarMecanicoEntaoRetornarSucesso() {
        when(mecanicoResponse.requestParaModel(mecanicoRequest)).thenReturn(mecanico);
        when(mecanicoRepository.save(mecanico)).thenReturn(mecanico);

        MecanicoDTO resultado = mecanicoService.cadastrarMecanico(mecanicoRequest);

        assertNotNull(resultado);
        verify(mecanicoRepository, times(1)).save(mecanico);
    }

    @Test
    void quandoAtualizarMecanicoEntaoRetornarMecanicoAtualizado() {
        when(mecanicoRepository.findById(mecanicoAntigo.getId())).thenReturn(Optional.of(mecanicoAntigo));
        when(mecanicoResponse.requestParaModel(mecanicoRequest)).thenReturn(mecanicoAtualizado);
        when(mecanicoRepository.save(mecanicoAtualizado)).thenReturn(mecanicoAtualizado);

        MecanicoDTO resultado = mecanicoService.atualizarMecanico(mecanicoAntigo.getId(), mecanicoRequest);

        verify(mecanicoRepository, times(1)).findById(mecanicoAntigo.getId());
        verify(mecanicoResponse, times(1)).requestParaModel(mecanicoRequest);
        verify(mecanicoRepository, times(1)).save(mecanicoAtualizado);

        assertNotNull(resultado);
        assertEquals(mecanicoAtualizado.getId(), resultado.getId());
        assertEquals(mecanicoAtualizado.getNome(), resultado.getNome());
    }

    @Test
    void quandoAtualizarMecanicoInexistenteEntaoLancarExcecao() {
        when(mecanicoRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            mecanicoService.atualizarMecanico(99L, mecanicoRequest);
        });

        assertEquals("Mecanico com id 99, nao encontrado", exception.getMessage());
        verify(mecanicoRepository, times(1)).findById(99L);
        verify(mecanicoResponse, never()).requestParaModel(any());
        verify(mecanicoRepository, never()).save(any());
    }

    @Test
    void quandoExcluirMecanicoEntaoDeveRemoverComSucesso() {
        when(mecanicoRepository.findById(mecanico.getId())).thenReturn(Optional.of(mecanico));

        mecanicoService.excluirMecanico(mecanico.getId());

        verify(mecanicoRepository, times(1)).findById(mecanico.getId());
        verify(mecanicoRepository, times(1)).delete(mecanico);
    }

    @Test
    void quandoExcluirMecanicoInexistenteEntaoLancarExcecao() {
        when(mecanicoRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            mecanicoService.excluirMecanico(99L);
        });

        assertEquals("Mecanico com id 99, nao encontrado", exception.getMessage());
        verify(mecanicoRepository, times(1)).findById(99L);
        verify(mecanicoRepository, never()).delete(any());
    }

    void startMecanico() {
        mecanicoAntigo = new Mecanico(2L, "Antigo Nome", "Especialidade Antiga", "104525", true, LocalDateTime.now(),
                LocalDateTime.now());
        mecanicoAtualizado = new Mecanico(3L, "Novo Nome", "Nova Especialidade", "104525", true, LocalDateTime.now(),
                LocalDateTime.now());
        mecanico = new Mecanico(1L, "João Mecânico", "Mecânico Geral", "revisao geral", false, LocalDateTime.now(),
                LocalDateTime.now());
        mecanicoRequest = new MecanicoRequest("Joao", "Especialidade Nova", "2311313123", true);

        mecanicos = new ArrayList<>();
        mecanicos.add(mecanico);
    }
}