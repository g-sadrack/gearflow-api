package com.sarlym.osmanager.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.api.dto.mapper.MecanicoMapper;
import com.sarlym.osmanager.api.dto.mapper.OrdemServicoMapper;
import com.sarlym.osmanager.api.dto.mapper.VeiculoMapper;
import com.sarlym.osmanager.api.dto.request.OrdemServicoRequest;
import com.sarlym.osmanager.api.dto.response.OrdemServicoDTO;
import com.sarlym.osmanager.api.dto.response.OrdemServicoResumo;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Mecanico;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private MecanicoService mecanicoService;
    private MecanicoMapper mecanicoMapper;
    private VeiculoService veiculoService;
    private VeiculoMapper veiculoMapper;
    private OrdemServicoMapper ordemServicoMapper;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, MecanicoService mecanicoService,
            VeiculoService veiculoService, ClienteService clienteService, OrdemServicoMapper ordemServicoMapper,
            MecanicoMapper mecanicoMapper, VeiculoMapper veiculoMapper) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.mecanicoService = mecanicoService;
        this.veiculoService = veiculoService;
        this.ordemServicoMapper = ordemServicoMapper;
        this.mecanicoMapper = mecanicoMapper;
        this.veiculoMapper = veiculoMapper;
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscaOrdemServicoOuErro(Long id) {
        return ordemServicoMapper.modeloParaDTO(ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Ordem de servico com id %d nao encontrada", id))));
    }

    public List<OrdemServicoResumo> buscaComFiltros(String numeroOs, Status status, Long veiculoId,
            LocalDateTime dataInicio, LocalDateTime dataFim) {
        return ordemServicoMapper
                .modeloListaParaListaDTOResumo(
                        ordemServicoRepository.find(numeroOs, status, veiculoId, dataInicio, dataFim));
    }

    @Transactional
    public OrdemServicoDTO salvar(OrdemServicoRequest request) {
        Mecanico mecanico = mecanicoMapper.dtoParaModel(mecanicoService.buscarMecanicoOuErro(request.getMecanico()));
        Veiculo veiculo = veiculoMapper.dtoParaModel(veiculoService.buscarVeiculoOuErro(request.getVeiculo()));

        OrdemServico os = ordemServicoMapper.requestParaModelo(request);
        System.out.println("\n\n" + os);
        os.setMecanico(mecanico); // Definido manualmente
        os.setVeiculo(veiculo); // Definido manualmente
        geradorNumOs(os);
        return ordemServicoMapper.modeloParaDTO(ordemServicoRepository.save(os));
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
    public OrdemServicoDTO alterarOrdemServico(Long id, OrdemServicoRequest ordemServicoRequest) {
        OrdemServico ordemServico = ordemServicoMapper.dtoParaModelo(buscaOrdemServicoOuErro(id));
        Veiculo veiculo = veiculoMapper
                .dtoParaModel(veiculoService.buscarVeiculoOuErro(ordemServicoRequest.getVeiculo()));
        Mecanico mecanico = mecanicoMapper
                .dtoParaModel(mecanicoService.buscarMecanicoOuErro(ordemServicoRequest.getMecanico()));

        ordemServico.setMecanico(mecanico);
        ordemServico.setVeiculo(veiculo);
        ordemServico.setDescricaoProblema(ordemServicoRequest.getDescricaoProblema());

        return ordemServicoMapper.modeloParaDTO(ordemServicoRepository.save(ordemServico));
    }

}
