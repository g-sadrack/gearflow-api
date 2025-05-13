// package com.sarlym.osmanager.service;

// import com.sarlym.osmanager.api.dto.mapper.ServicoMapper;
// import com.sarlym.osmanager.api.dto.request.ServicoRequest;
// import com.sarlym.osmanager.domain.exception.NegocioException;
// import com.sarlym.osmanager.domain.model.Servico;
// import com.sarlym.osmanager.domain.repository.ServicoRepository;
// import com.sarlym.osmanager.domain.service.ServicoService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @SpringBootTest
// class ServicoServiceTest {

//     @Mock
//     private Servico servico;
//     @Mock
//     private Servico servicoAntigo;
//     @Mock
//     private Servico servicoAtualizado;
//     @Mock
//     private ServicoMapper servicoResponse;
//     @Mock
//     private ServicoRepository servicoRepository;
//     @Mock
//     private ServicoRequest servicoRequest;
//     @Mock
//     private List<Servico> servicos;

//     private ServicoService servicoService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         servicoService = new ServicoService(servicoRepository, servicoResponse);
//         startServico();
//     }

//     @Test
//     void quandoBuscarServicoPorIdRetornarApenasUm() {
//         when(servicoRepository.findById(anyLong())).thenReturn(Optional.of(servico));

//         Servico resultado = servicoService.buscarServicoOuErro(1L);

//         assertNotNull(resultado);
//         assertEquals(servico.getId(), resultado.getId());
//         verify(servicoRepository, times(1)).findById(servico.getId());
//     }

//     @Test
//     void quandoBuscarServicoInexistenteRetornarErro() {
//         when(servicoRepository.findById(anyLong())).thenReturn(Optional.empty());

//         NegocioException exception = assertThrows(NegocioException.class, () -> {
//             servicoService.buscarServicoOuErro(2L);
//         });

//         assertEquals("Servico nao encontrado", exception.getMessage());
//         verify(servicoRepository, times(1)).findById(2L);
//     }

//     @Test
//     void quandoListarServicosEntaoRetornarSucesso() {
//         when(servicoRepository.findAll()).thenReturn(servicos);

//         List<Servico> resultado = servicoService.listarServicos();

//         assertNotNull(resultado);
//     }

//     @Test
//     void deveRetornarListaVaziaQuandoNaoExistiremServicos() {
//         when(servicoRepository.findAll()).thenReturn(List.of());

//         List<Servico> resultado = servicoService.listarServicos();

//         assertNotNull(resultado);
//         assertTrue(resultado.isEmpty());
//         verify(servicoRepository, times(1)).findAll();
//     }

//     @Test
//     @Transactional
//     void quandoCadastrarServicoEntaoRetornarSucesso() {
//         when(servicoResponse.paraModel(servicoRequest)).thenReturn(servico);
//         when(servicoRepository.save(servico)).thenReturn(servico);

//         Servico resultado = servicoService.cadastrarServico(servicoRequest);

//         assertNotNull(resultado);
//         verify(servicoRepository, times(1)).save(servico);
//     }

//     @Test
//     @Transactional
//     void quandoAlterarServicoEntaoRetornarServicoAtualizado() {
//         when(servicoRepository.findById(servicoAntigo.getId())).thenReturn(Optional.of(servicoAntigo));
//         when(servicoResponse.paraModel(servicoRequest)).thenReturn(servicoAtualizado);
//         when(servicoRepository.save(servicoAtualizado)).thenReturn(servicoAtualizado);

//         Servico resultado = servicoService.alterarServico(servicoAntigo.getId(), servicoRequest);

//         verify(servicoRepository, times(1)).findById(servicoAntigo.getId());
//         verify(servicoResponse, times(1)).paraModel(servicoRequest);
//         verify(servicoRepository, times(1)).save(servicoAtualizado);

//         assertNotNull(resultado);
//         assertEquals(servicoAtualizado.getId(), resultado.getId());
//         assertEquals(servicoAtualizado.getDescricao(), resultado.getDescricao());
//     }

//     @Test
//     @Transactional
//     void quandoAlterarServicoInexistenteEntaoLancarExcecao() {
//         when(servicoRepository.findById(anyLong())).thenReturn(Optional.empty());

//         NegocioException exception = assertThrows(NegocioException.class, () -> {
//             servicoService.alterarServico(99L, servicoRequest);
//         });

//         assertEquals("Servico nao encontrado", exception.getMessage());
//         verify(servicoRepository, times(1)).findById(99L);
//         verify(servicoResponse, never()).paraModel(any());
//         verify(servicoRepository, never()).save(any());
//     }

//     @Test
//     @Transactional
//     void quandoExcluirServicoEntaoDeveRemoverComSucesso() {
//         when(servicoRepository.findById(servico.getId())).thenReturn(Optional.of(servico));

//         servicoService.excluirServico(servico.getId());

//         verify(servicoRepository, times(1)).findById(servico.getId());
//         verify(servicoRepository, times(1)).delete(servico);
//         verify(servicoRepository, times(1)).flush();
//     }

//     @Test
//     @Transactional
//     void quandoExcluirServicoInexistenteEntaoLancarExcecao() {
//         when(servicoRepository.findById(anyLong())).thenReturn(Optional.empty());

//         NegocioException exception = assertThrows(NegocioException.class, () -> {
//             servicoService.excluirServico(99L);
//         });

//         assertEquals("Servico nao encontrado", exception.getMessage());
//         verify(servicoRepository, times(1)).findById(99L);
//         verify(servicoRepository, never()).delete(any());
//     }

//     void startServico() {
//         servicoAntigo = new Servico();
//         servicoAntigo.setId(2L);
//         servicoAntigo.setDescricao("Serviço Antigo");

//         servicoAtualizado = new Servico();
//         servicoAtualizado.setId(3L);
//         servicoAtualizado.setDescricao("Serviço Atualizado");

//         servico = new Servico();
//         servico.setId(1L);
//         servico.setDescricao("Serviço Teste");

//         servicoRequest = new ServicoRequest();
//         servicoRequest.setDescricao("Novo Serviço");

//         servicos = new ArrayList<>();
//         servicos.add(servico);
//     }
// }