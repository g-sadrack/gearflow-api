package com.sarlym.osmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
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

import com.sarlym.osmanager.api.dto.mapper.ClienteMapper;
import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.domain.exception.ClienteException;
import com.sarlym.osmanager.domain.exception.EmailJaExistenteException;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.model.Veiculo;
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

    private Cliente cliente;
    private List<Veiculo> veiculos = null;
    private ClienteRequest clienteRequest;

    @BeforeEach
    void setUp() {

        cliente = new Cliente(1L, "Jonas", "40028922", "profeta@gmail.com", "75229917067", LocalDateTime.now(),
                LocalDateTime.now(), veiculos);
        clienteRequest = new ClienteRequest("João", "00289044", "31021769096", "joaum@gmail.com");

    }

    @Test
    void deve_RetornarCliente_QuandoExistir() {
        // Arrange
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Act
        Cliente resultado = clienteService.buscarClienteOuErro(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Jonas", resultado.getNome());
        verify(clienteRepository).findById(1L);
    }

    @Test
    void deve_LancarExcecao_quandoNaoExiste() {
        // Arrange
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteException.class, () -> clienteService.buscarClienteOuErro(1L));
        verify(clienteRepository).findById(1L);
    }

    @Test
    void deve_RetornarListaDeClientes_quandoExistir() {
        // Arrange
        List<Cliente> listaClientes = List.of(cliente);
        when(clienteRepository.findAll()).thenReturn(listaClientes);

        // Act
        List<Cliente> resultado = clienteService.clientes();

        // Assert
        assertEquals(1, resultado.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void deve_SalvarCliente_quandoEmailNaoExiste() {
        // Arrange
        when(clienteMapper.requestParaModel(clienteRequest)).thenReturn(cliente);
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Act
        Cliente resultado = clienteService.cadastrarCliente(clienteRequest);

        // Assert
        assertNotNull(resultado);
        verify(clienteRepository).existsByEmail(cliente.getEmail());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deve_LancarExcecao_quandoEmailJaExiste() {
        // Arrange
        when(clienteMapper.requestParaModel(clienteRequest)).thenReturn(cliente);
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailJaExistenteException.class, () -> clienteService.cadastrarCliente(clienteRequest));
        verify(clienteRepository).existsByEmail(cliente.getEmail());
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deve_AtualizarCliente_quandoExiste() {
        // Arrange
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteMapper).copiaParaNovo(clienteRequest, cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Act
        Cliente resultado = clienteService.alterarCliente(1L, clienteRequest);

        // Assert
        assertNotNull(resultado);
        verify(clienteMapper).copiaParaNovo(clienteRequest, cliente);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deve_RemoverCliente_quandoExiste() {
        // Arrange
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);
        doNothing().when(clienteRepository).flush();

        // Act
        clienteService.deletarCliente(1L);

        // Assert
        verify(clienteRepository).delete(cliente);
        verify(clienteRepository).flush();
    }
}
