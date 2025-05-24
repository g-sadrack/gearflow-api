// package com.sarlym.osmanager.service;

// import com.sarlym.osmanager.api.dto.mapper.VeiculoMapper;
// import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
// import com.sarlym.osmanager.domain.exception.NegocioException;
// import com.sarlym.osmanager.domain.model.Cliente;
// import com.sarlym.osmanager.domain.model.Veiculo;
// import com.sarlym.osmanager.domain.repository.VeiculoRepository;
// import com.sarlym.osmanager.domain.service.VeiculoService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.*;

// @SpringBootTest
// class VeiculoServiceTest {

//     @Mock
//     private Veiculo veiculo;
//     @Mock
//     private Veiculo veiculoAntigo;
//     @Mock
//     private Veiculo veiculoAtualizado;
//     @Mock
//     private VeiculoMapper veiculoResponse;
//     @Mock
//     private VeiculoRepository veiculoRepository;
//     @Mock
//     private VeiculoRequest veiculoRequest;
//     @Mock
//     private List<Veiculo> veiculos;

//     private VeiculoService veiculoService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         veiculoService = new VeiculoService(veiculoRepository, veiculoResponse);
//         startVeiculo();
//     }

//     @Test
//     void quandoBuscarVeiculoPorIdRetornarApenasUm() {
//         when(veiculoRepository.findById(anyLong())).thenReturn(Optional.of(veiculo));

//         Veiculo resultado = veiculoService.buscarVeiculoOuErro(1L);

//         assertNotNull(resultado);
//         assertEquals(veiculo.getId(), resultado.getId());
//         verify(veiculoRepository, times(1)).findById(veiculo.getId());
//     }

//     @Test
//     void quandoBuscarVeiculoInexistenteRetornarErro() {
//         Long idInexistente = 99L;
//         when(veiculoRepository.findById(idInexistente)).thenReturn(Optional.empty());

//         NegocioException exception = assertThrows(NegocioException.class, () -> {
//             veiculoService.buscarVeiculoOuErro(idInexistente);
//         });

//         assertEquals("Veiculo com id 99 nao encontrado", exception.getMessage());
//         verify(veiculoRepository, times(1)).findById(idInexistente);
//     }

//     @Test
//     void quandoListarVeiculosEntaoRetornarSucesso() {
//         when(veiculoRepository.findAll()).thenReturn(veiculos);

//         List<Veiculo> resultado = veiculoService.listarVeiculos();

//         assertNotNull(resultado);
//     }

//     @Test
//     void deveRetornarListaVaziaQuandoNaoExistiremVeiculos() {
//         when(veiculoRepository.findAll()).thenReturn(List.of());

//         List<Veiculo> resultado = veiculoService.listarVeiculos();

//         assertNotNull(resultado);
//         assertTrue(resultado.isEmpty());
//         verify(veiculoRepository, times(1)).findAll();
//     }

//     @Test
//     @Transactional
//     void quandoCadastrarVeiculoEntaoRetornarSucesso() {
//         when(veiculoResponse.paraModel(veiculoRequest)).thenReturn(veiculo);
//         when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

//         Veiculo resultado = veiculoService.cadastrarVeiculo(veiculoRequest);

//         assertNotNull(resultado);
//         verify(veiculoRepository, times(1)).save(veiculo);
//     }

//     @Test
//     @Transactional
//     void quandoAlterarVeiculoEntaoRetornarVeiculoAtualizado() {
//         when(veiculoRepository.findById(veiculoAntigo.getId())).thenReturn(Optional.of(veiculoAntigo));
//         when(veiculoResponse.paraModel(veiculoRequest)).thenReturn(veiculoAtualizado);
//         when(veiculoRepository.save(veiculoAtualizado)).thenReturn(veiculoAtualizado);

//         Veiculo resultado = veiculoService.alterarVeiculo(veiculoAntigo.getId(), veiculoRequest);

//         verify(veiculoRepository, times(1)).findById(veiculoAntigo.getId());
//         verify(veiculoResponse, times(1)).paraModel(veiculoRequest);
//         verify(veiculoRepository, times(1)).save(veiculoAtualizado);

//         assertNotNull(resultado);
//         assertEquals(veiculoAtualizado.getId(), resultado.getId());
//         assertEquals(veiculoAtualizado.getPlaca(), resultado.getPlaca());
//     }

//     @Test
//     @Transactional
//     void quandoAlterarVeiculoInexistenteEntaoLancarExcecao() {
//         Long idInexistente = 99L;
//         when(veiculoRepository.findById(idInexistente)).thenReturn(Optional.empty());

//         NegocioException exception = assertThrows(NegocioException.class, () -> {
//             veiculoService.alterarVeiculo(idInexistente, veiculoRequest);
//         });

//         assertEquals("Veiculo com id 99 nao encontrado", exception.getMessage());
//         verify(veiculoRepository, times(1)).findById(idInexistente);
//         verify(veiculoResponse, never()).paraModel(any());
//         verify(veiculoRepository, never()).save(any());
//     }

//     @Test
//     @Transactional
//     void quandoDeletarVeiculoEntaoDeveRemoverComSucesso() {
//         when(veiculoRepository.findById(veiculo.getId())).thenReturn(Optional.of(veiculo));

//         veiculoService.deletarVeiculo(veiculo.getId());

//         verify(veiculoRepository, times(1)).findById(veiculo.getId());
//         verify(veiculoRepository, times(1)).delete(veiculo);
//         verify(veiculoRepository, times(1)).flush();
//     }

//     @Test
//     @Transactional
//     void quandoDeletarVeiculoInexistenteEntaoLancarExcecao() {
//         Long idInexistente = 99L;
//         when(veiculoRepository.findById(idInexistente)).thenReturn(Optional.empty());

//         NegocioException exception = assertThrows(NegocioException.class, () -> {
//             veiculoService.deletarVeiculo(idInexistente);
//         });

//         assertEquals("Veiculo com id 99 nao encontrado", exception.getMessage());
//         verify(veiculoRepository, times(1)).findById(idInexistente);
//         verify(veiculoRepository, never()).delete(any());
//     }

//     void startVeiculo() {
//         Cliente cliente =  new Cliente();
//         // Veículo Antigo
//         veiculoAntigo = new Veiculo(
//                 2L,
//                 "ABC1D23",
//                 "Ford",
//                 "Ranger",
//                 2020,
//                 "Prata",
//                 35000,
//                 LocalDateTime.parse("2024-01-01T10:00:00"),
//                 LocalDateTime.parse("2024-05-01T15:30:00"),
//                 cliente
//                 );

//         // Veículo Atualizado
//         veiculoAtualizado = new Veiculo(
//                 3L,
//                 "XYZ9Z99",
//                 "Toyota",
//                 "Hilux",
//                 2023,
//                 "Preto",
//                 15000,
//                 LocalDateTime.now(),
//                 LocalDateTime.now(),
//                 cliente);

//         // Veículo Básico
//         veiculo = new Veiculo(
//                 1L,
//                 "DEF4E56",
//                 "Chevrolet",
//                 "S10",
//                 2022,
//                 "Branco",
//                 20000,
//                 LocalDateTime.parse("2024-03-15T09:15:00"),
//                 LocalDateTime.parse("2024-05-20T14:00:00"),
//                 cliente);

//         // Request para teste
//         veiculoRequest = new VeiculoRequest();
//         veiculoRequest.setPlaca("NOV4A00");
//         veiculoRequest.setMarca("Volkswagen");
//         veiculoRequest.setModelo("Amarok");
//         veiculoRequest.setAno(2024);
//         veiculoRequest.setCor("Azul");
//         veiculoRequest.setQuilometragem(0);

//         // Lista de veículos
//         veiculos = new ArrayList<>();
//         veiculos.add(veiculo);
//         veiculos.add(veiculoAntigo);
//     }
// }