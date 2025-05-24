package com.sarlym.osmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sarlym.osmanager.api.dto.mapper.ClienteMapper;
import com.sarlym.osmanager.api.dto.response.ClienteDTO;
import com.sarlym.osmanager.domain.exception.ClienteException;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
import com.sarlym.osmanager.domain.service.ClienteService;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteEntity;
    private ClienteDTO clienteDto;

    @BeforeEach
    void setup() {
        // Dados de exemplo
        clienteEntity = new Cliente();
        clienteEntity.setId(42L);
        clienteEntity.setNome("João Silva");
        // ... definir demais campos se quiser

        clienteDto = new ClienteDTO();
        clienteDto.setId(42L);
        clienteDto.setNome("João Silva");
        // ... definir demais campos do DTO
    }

    @Test
    void deveRetornarClienteDTOQuandoExistir() {
        // Arrange
        when(clienteRepository.findById(42L)).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.modelParaDTO(clienteEntity)).thenReturn(clienteDto);

        // Act
        ClienteDTO resultado = clienteService.buscarClienteOuErro(42L);

        // Assert
        assertNotNull(resultado, "Deve retornar um DTO não-nulo");
        assertEquals(42L, resultado.getId());
        assertEquals("João Silva", resultado.getNome());
        verify(clienteRepository, times(1)).findById(42L);
        verify(clienteMapper, times(1)).modelParaDTO(clienteEntity);
    }

    @Test
    void deveLancarExceptionQuandoNaoExistirCliente() {
        // Arrange
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        ClienteException ex = assertThrows(
            ClienteException.class,
            () -> clienteService.buscarClienteOuErro(99L),
            "Deve lançar ClienteException quando não encontrar"
        );
        assertEquals(
            "Cliente não pode ser deletado pois não foi encontrado",
            ex.getMessage()
        );
        verify(clienteRepository, times(1)).findById(99L);
        verifyNoInteractions(clienteMapper);
    }
    
}
