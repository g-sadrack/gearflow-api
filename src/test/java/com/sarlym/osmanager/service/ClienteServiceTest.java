package com.sarlym.osmanager.service;

import com.sarlym.osmanager.api.dto.request.ClienteRequest;
import com.sarlym.osmanager.api.dto.response.ClienteResponse;
import com.sarlym.osmanager.domain.exception.ClienteException;
import com.sarlym.osmanager.domain.exception.EmailJaExistenteException;
import com.sarlym.osmanager.domain.model.Cliente;
import com.sarlym.osmanager.domain.repository.ClienteRepository;
import com.sarlym.osmanager.domain.service.ClienteService;
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
class ClienteServiceTest {
    @Mock
    private Cliente cliente;
    @Mock
    private Cliente clienteAntigo;
    @Mock
    private Cliente clienteAtualizado;
    @Mock
    private ClienteResponse clienteResponse;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteRequest clienteRequest;
    @Mock
    private List<Cliente> clientes;

    private ClienteService clienteService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteService( clienteRepository,  clienteResponse);
        startCliente();
    }

    @Test
    void quandoBuscarClientePorIdRetonarApenasUm(){
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Cliente cliente1 =  clienteService.buscarClienteOuErro(1L);

        assertNotNull(cliente1);
        assertEquals(cliente.getId(), cliente1.getId());
        verify(clienteRepository, times(1)).findById(cliente.getId());
    }

    @Test
    void quandoBuscarClienteInexistenteRetornarErro(){
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ClienteException exception = assertThrows(ClienteException.class, () -> {
            clienteService.buscarClienteOuErro(2L);
        });


        assertEquals("Cliente com ID "+ 2L +" não encontrado", exception.getMessage());
        verify(clienteRepository, times(1)).findById(2L);
    }

    @Test
    void quandoListarClienteEntaoRetornarSucesso(){
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> clienteList = clienteService.clientes();

        assertNotNull(clienteList);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of());

        List<Cliente> clienteList = clienteService.clientes();

        assertNotNull(clienteList);
        assertTrue(clienteList.isEmpty());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void quandoCadastrarClienteEntaoReotrnarSucesso(){
        when(clienteResponse.paraModel(clienteRequest)).thenReturn(cliente);
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteTeste = clienteService.cadastrarCliente(clienteRequest);

        assertNotNull(clienteTeste);
        verify(clienteRepository, times(1)).existsByEmail(cliente.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExistir() {
        when(clienteResponse.paraModel(clienteRequest)).thenReturn(cliente);
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(true);

        EmailJaExistenteException exception = assertThrows(EmailJaExistenteException.class,
                () -> clienteService.cadastrarCliente(clienteRequest));

        assertEquals("Email " + cliente.getEmail() + " já cadastrado no sistema", exception.getMessage());
        verify(clienteRepository, times(1)).existsByEmail(cliente.getEmail());
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void quandoAlterarClienteEntaoRetornarClienteAtualizado() {
        when(clienteRepository.findById(clienteAntigo.getId())).thenReturn(Optional.of(clienteAntigo));
        when(clienteResponse.paraModel(clienteRequest)).thenReturn(clienteAtualizado);
        when(clienteRepository.save(clienteAtualizado)).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.alterarCliente(clienteAntigo.getId(), clienteRequest);

        verify(clienteRepository, times(1)).findById(clienteAntigo.getId());
        verify(clienteResponse, times(1)).paraModel(clienteRequest);
        verify(clienteRepository, times(1)).save(clienteAtualizado);

        assertNotNull(resultado);
        assertEquals(clienteAtualizado.getId(), resultado.getId());
        assertEquals(clienteAtualizado.getNome(), resultado.getNome());
    }

    @Test
    void quandoAlterarClienteInexistenteEntaoLancarExcecao() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ClienteException exception = assertThrows(ClienteException.class, () -> {
            clienteService.alterarCliente(99L, clienteRequest);
        });

        assertEquals("Cliente com ID 99 não encontrado", exception.getMessage());

        verify(clienteRepository, times(1)).findById(99L);
        verify(clienteResponse, never()).paraModel(any());
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void quandoExcluirClienteEntaoDeveRemoverComSucesso() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));

        clienteService.deletarCliente(cliente.getId());

        verify(clienteRepository, times(1)).findById(cliente.getId());
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    void quandoExcluirClienteInexistenteEntaoLancarExcecao() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ClienteException exception = assertThrows(ClienteException.class, () -> {
            clienteService.deletarCliente(99L);
        });

        assertEquals("Cliente não pode ser deletado pois não foi encontrado", exception.getMessage());

        verify(clienteRepository, times(1)).findById(99L);
        verify(clienteRepository, never()).delete(any());
    }

    void startCliente() {
        clienteAntigo = new Cliente(2L, "Antigo Nome", "61 98544-8654", "antigo@email.com", LocalDateTime.now(), LocalDateTime.now());
        clienteAtualizado = new Cliente(3L, "Novo Nome", "61 98544-8654", "novo@email.com", LocalDateTime.now(), LocalDateTime.now());
        cliente = new Cliente(1L, "Guarda Belo", "61 98544-8654", "guarda22belo@gmail.com", LocalDateTime.now(), LocalDateTime.now());
        clienteRequest = new ClienteRequest("Batatinha", "61 99851-3445", "batatinha123@email.com");

        clientes = new ArrayList<>();
        clientes.add(cliente);
    }

}
