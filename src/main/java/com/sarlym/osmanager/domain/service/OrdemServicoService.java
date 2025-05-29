package com.sarlym.osmanager.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.mapper.OrdemServicoMapper;
import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private MecanicoService mecanicoService;
    private VeiculoService veiculoService;
    private OrdemServicoMapper ordemServicoMapper;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, MecanicoService mecanicoService,
            VeiculoService veiculoService, ClienteService clienteService, OrdemServicoMapper ordemServicoMapper) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.mecanicoService = mecanicoService;
        this.veiculoService = veiculoService;
        this.ordemServicoMapper = ordemServicoMapper;
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
        System.out.println("\n\n" + os);
        os.setMecanico(mecanico); // Definido manualmente
        os.setVeiculo(veiculo); // Definido manualmente
        geradorNumOs(os);
        return ordemServicoRepository.save(os);
    }

    public void geradorNumOs(OrdemServico os) {
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter padraoCodigoOs = DateTimeFormatter.ofPattern("MMddHHmmss");

        String sufixoCodigo = dataAtual.format(padraoCodigoOs);
        int prefixoCodigo = dataAtual.getYear();

        String codigoOs = prefixoCodigo + "-" + sufixoCodigo;
        os.setNumero_os(codigoOs);
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
        os.setAtivo(false);
        os.setDataFinalizacao(LocalDateTime.now());
        os.setStatus(Status.FINALIZADA);
        ordemServicoRepository.save(os);
    }

}
