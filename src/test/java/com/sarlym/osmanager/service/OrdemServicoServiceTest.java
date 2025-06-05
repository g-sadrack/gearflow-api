package com.sarlym.osmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.mapper.OrdemServicoMapper;
import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repositories.OrdemServicoRepository;
import com.sarlym.osmanager.domain.service.MecanicoService;
import com.sarlym.osmanager.domain.service.OrdemServicoService;
import com.sarlym.osmanager.domain.service.VeiculoService;

@ExtendWith(MockitoExtension.class)
class OrdemServicoServiceTest {

    @Mock
    private OrdemServicoRepository ordemServicoRepository;

    @Mock
    private MecanicoService mecanicoService;

    @Mock
    private VeiculoService veiculoService;

    @Mock
    private OrdemServicoMapper ordemServicoMapper;

    @InjectMocks
    private OrdemServicoService ordemServicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_RetornarOrdemServico_quandoExistir() {
        OrdemServico os = new OrdemServico();
        os.setId(1L);
        when(ordemServicoRepository.findById(1L)).thenReturn(Optional.of(os));

        OrdemServico result = ordemServicoService.buscaOrdemServicoOuErro(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void deve_LancarExcecao_quandoOrdemServicoNaoExistir() {
        when(ordemServicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> ordemServicoService.buscaOrdemServicoOuErro(1L));
    }

    @Test
    void deve_RetornarListaDeOrdemServicoAtivos() {
        OrdemServico os1 = new OrdemServico(); os1.setAtivo(true);
        OrdemServico os2 = new OrdemServico(); os2.setAtivo(false);
        OrdemServico os3 = new OrdemServico(); os3.setAtivo(true);

        when(ordemServicoRepository.findAll()).thenReturn(Arrays.asList(os1, os2, os3));

        List<OrdemServico> ativos = ordemServicoService.buscaListaAtivos();

        assertEquals(2, ativos.size());
        assertTrue(ativos.stream().allMatch(OrdemServico::getAtivo));
    }

    @Test
    void deve_SalvarOrdemServico_quandoDadosValidos() {
        OrdemServicoRequest request = mock(OrdemServicoRequest.class);
        Mecanico mecanico = new Mecanico();
        Veiculo veiculo = new Veiculo();
        OrdemServico os = new OrdemServico();

        when(request.getMecanico()).thenReturn(1L);
        when(request.getVeiculo()).thenReturn(2L);
        when(mecanicoService.buscarMecanicoOuErro(1L)).thenReturn(mecanico);
        when(veiculoService.buscarVeiculoOuErro(2L)).thenReturn(veiculo);
        when(ordemServicoMapper.requestParaModelo(request)).thenReturn(os);
        when(ordemServicoRepository.save(any())).thenReturn(os);

        OrdemServico resultado = ordemServicoService.salvar(request);

        assertNotNull(resultado);
        verify(ordemServicoRepository).save(os);
    }

    @Test
    void deve_AlterarOrdemServico_quandoExistir() {
        OrdemServicoRequest request = mock(OrdemServicoRequest.class);
        OrdemServico existente = new OrdemServico(); existente.setId(1L);
        Mecanico mecanico = new Mecanico();
        Veiculo veiculo = new Veiculo();

        when(request.getMecanico()).thenReturn(1L);
        when(request.getVeiculo()).thenReturn(2L);
        when(ordemServicoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(veiculoService.buscarVeiculoOuErro(2L)).thenReturn(veiculo);
        when(mecanicoService.buscarMecanicoOuErro(1L)).thenReturn(mecanico);
        when(ordemServicoRepository.save(any())).thenReturn(existente);

        OrdemServico atualizado = ordemServicoService.alterarOrdemServico(1L, request);

        assertNotNull(atualizado);
        verify(ordemServicoRepository).save(existente);
    }

    @Test
    void deve_FinalizarOrdemServico_quandoAtiva() {
        OrdemServico os = new OrdemServico();
        os.setAtivo(true);

        when(ordemServicoRepository.findById(1L)).thenReturn(Optional.of(os));

        ordemServicoService.deletaOrdemServico(1L);

        assertFalse(os.getAtivo());
        assertEquals(Status.FINALIZADA, os.getStatus());
        assertNotNull(os.getDataFinalizacao());
        verify(ordemServicoRepository).save(os);
    }

    @Test
    void deve_NaoFinalizarOrdemServico_quandoInativa() {
        OrdemServico os = new OrdemServico();
        os.setAtivo(false);

        when(ordemServicoRepository.findById(1L)).thenReturn(Optional.of(os));

        ordemServicoService.deletaOrdemServico(1L);

        verify(ordemServicoRepository, never()).save(os);
    }
} 

