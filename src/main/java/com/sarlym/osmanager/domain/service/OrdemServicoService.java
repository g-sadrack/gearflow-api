package com.sarlym.osmanager.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.mapper.OrdemServicoMapper;
import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.api.dto.request.PecaOrdemServicoRequest;
import com.sarlym.osmanager.api.dto.request.ServicoPrestadoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.model.Produto;
import com.sarlym.osmanager.domain.model.ProdutoOrdemServico;
import com.sarlym.osmanager.domain.model.Servico;
import com.sarlym.osmanager.domain.model.ServicoPrestado;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repositories.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private MecanicoService mecanicoService;
    private VeiculoService veiculoService;
    private OrdemServicoMapper ordemServicoMapper;
    private ServicoService servicoService;
    private ProdutoService produtoService;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, MecanicoService mecanicoService,
            VeiculoService veiculoService, ClienteService clienteService, OrdemServicoMapper ordemServicoMapper,
            ServicoService servicoService,
            ProdutoService produtoService) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.mecanicoService = mecanicoService;
        this.veiculoService = veiculoService;
        this.ordemServicoMapper = ordemServicoMapper;
        this.servicoService = servicoService;
        this.produtoService = produtoService;
    }

    @Transactional(readOnly = true)
    public OrdemServico buscaOrdemServicoOuErro(Long id) {
        return ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Ordem de servico com id %d nao encontrada", id)));
    }

    public List<OrdemServico> buscaComFiltros(String numeroOs, Status status, Long veiculoId,
            LocalDateTime dataInicio, LocalDateTime dataFim) {
        return ordemServicoRepository.find(numeroOs, status, veiculoId, dataInicio, dataFim);
    }

    public List<OrdemServico> buscaListaAtivos() {
        List<OrdemServico> osList = ordemServicoRepository.findAll();
        return osList.stream().filter(x -> x.getAtivo()).collect(Collectors.toList());
    }

    @Transactional
    public OrdemServico salvar(OrdemServicoRequest request) {
        Mecanico mecanico = mecanicoService.buscarMecanicoOuErro(request.getMecanico());
        Veiculo veiculo = veiculoService.buscarVeiculoOuErro(request.getVeiculo());

        OrdemServico os = ordemServicoMapper.requestParaModelo(request);

        os.setMecanico(mecanico);
        os.setVeiculo(veiculo);
        geradorNumOs(os);
        return ordemServicoRepository.save(os);
    }

    public void geradorNumOs(OrdemServico os) {
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter padraoCodigoOs = DateTimeFormatter.ofPattern("MMddHHmmss");

        String sufixoCodigo = dataAtual.format(padraoCodigoOs);
        int prefixoCodigo = dataAtual.getYear();

        String codigoOs = prefixoCodigo + "-" + sufixoCodigo;
        os.setNumeroOs(codigoOs);
    }

    @Transactional
    public OrdemServico alterarOrdemServico(Long id, OrdemServicoRequest ordemServicoRequest) {
        OrdemServico ordemServico = buscaOrdemServicoOuErro(id);
        Veiculo veiculo = veiculoService.buscarVeiculoOuErro(ordemServicoRequest.getVeiculo());
        Mecanico mecanico = mecanicoService.buscarMecanicoOuErro(ordemServicoRequest.getMecanico());

        ordemServico.setMecanico(mecanico);
        ordemServico.setVeiculo(veiculo);
        ordemServico.setDescricaoProblema(ordemServicoRequest.getDescricaoProblema());

        return ordemServicoRepository.save(ordemServico);
    }

    @Transactional
    public void deletaOrdemServico(Long id) {
        OrdemServico os = buscaOrdemServicoOuErro(id);
        if (os.getAtivo() == Boolean.TRUE) {
            os.setAtivo(false);
            os.setDataFinalizacao(LocalDateTime.now());
            os.setStatus(Status.FINALIZADA);
            ordemServicoRepository.save(os);
        }
    }

    @Transactional
    public OrdemServico adicionaServico(Long id, ServicoPrestadoRequest request) {
        // busca OS existente
        OrdemServico ordemServico = buscaOrdemServicoOuErro(id);
        // busca de servico existente
        Servico servico = servicoService.buscarServicoOuErro(request.getServico());

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setOrdemServico(ordemServico);
        servicoPrestado.setServico(servico);
        servicoPrestado.setValorServicoPrestado(request.getValorServicoPrestado());
        servicoPrestado.setObservacoes(request.getObservacoes());

        ordemServico.getServicos().add(servicoPrestado);
        calculaValorTotal(ordemServico);

        return ordemServicoRepository.save(ordemServico);
    }

    @Transactional
    public OrdemServico adicionaProduto(Long id, PecaOrdemServicoRequest request) {
        OrdemServico ordemServico = buscaOrdemServicoOuErro(id);
        Produto peca = produtoService.buscaProdutoOuErro(request.getProduto());

        ProdutoOrdemServico pecaOrdemServico = new ProdutoOrdemServico();
        pecaOrdemServico.setOrdemServico(ordemServico);
        pecaOrdemServico.setProduto(peca);
        pecaOrdemServico.setQuantidade(request.getQuantidade());
        pecaOrdemServico.setValor(request.getValorUnitario());

        ordemServico.getProdutos().add(pecaOrdemServico);
        calculaValorTotal(ordemServico);

        return ordemServicoRepository.save(ordemServico);
    }

    public void calculaValorTotal(OrdemServico ordemServico) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ProdutoOrdemServico> produtos = ordemServico.getProdutos();
        List<ServicoPrestado> servicos = ordemServico.getServicos();

        for (ProdutoOrdemServico produto : produtos) {
            BigDecimal valorProduto = produto.getValor().multiply(
                    BigDecimal.valueOf(produto.getQuantidade()));
            ;
            valorTotal = valorTotal.add(valorProduto);
        }

        for (ServicoPrestado servicoPrestado : servicos) {
            BigDecimal valor = Optional.ofNullable(servicoPrestado.getValorServicoPrestado()).orElse(BigDecimal.ZERO);
            valorTotal = valorTotal.add(valor);
        }

        ordemServico.setValorTotal(valorTotal);
    }

}
