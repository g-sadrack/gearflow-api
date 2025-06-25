package com.sarlym.gearflowapi.service;

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

import com.sarlym.gearflowapi.api.dto.mapper.VeiculoMapper;
import com.sarlym.gearflowapi.api.dto.request.VeiculoRequest;
import com.sarlym.gearflowapi.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.gearflowapi.domain.model.Veiculo;
import com.sarlym.gearflowapi.domain.repositories.VeiculoRepository;
import com.sarlym.gearflowapi.domain.service.VeiculoService;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;
    private VeiculoRequest veiculoRequest;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setModelo("Civic");

        veiculoRequest = new VeiculoRequest();
        veiculoRequest.setModelo("Civic");
    }

    @Test
    void deve_RetornarVeiculo_quandoExistir() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        // Act
        Veiculo resultado = veiculoService.buscarVeiculoOuErro(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Civic", resultado.getModelo());
        verify(veiculoRepository).findById(1L);
    }

    @Test
    void deve_LancarExcecao_quandoNaoEncontrado() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> veiculoService.buscarVeiculoOuErro(1L)
        );

        assertEquals("Veiculo com id 1 nao encontrado", exception.getMessage());
        verify(veiculoRepository).findById(1L);
    }

    @Test
    void deve_RetornarListaDeVeiculos_quandoExistir() {
        // Arrange
        List<Veiculo> lista = List.of(veiculo);
        when(veiculoRepository.findAll()).thenReturn(lista);

        // Act
        List<Veiculo> resultado = veiculoService.listarVeiculos();

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("Civic", resultado.get(0).getModelo());
        verify(veiculoRepository).findAll();
    }

    @Test
    void deve_SalvarVeiculo_quandoRequestValido() {
        // Arrange
        when(veiculoMapper.requestParaModel(veiculoRequest)).thenReturn(veiculo);
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        // Act
        Veiculo resultado = veiculoService.cadastrarVeiculo(veiculoRequest);

        // Assert
        assertNotNull(resultado);
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    void deve_AtualizarVeiculo_quandoExistir() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        doNothing().when(veiculoMapper).copiaParaNovo(veiculoRequest, veiculo);
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        // Act
        Veiculo resultado = veiculoService.alterarVeiculo(1L, veiculoRequest);

        // Assert
        assertNotNull(resultado);
        verify(veiculoRepository).save(veiculo);
        verify(veiculoMapper).copiaParaNovo(veiculoRequest, veiculo);
    }

}