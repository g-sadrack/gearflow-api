package com.sarlym.osmanager.api.controller;

import com.sarlym.osmanager.api.dto.mapper.VeiculoMapper;
import com.sarlym.osmanager.api.dto.request.VeiculoRequest;
import com.sarlym.osmanager.api.dto.response.VeiculoDTO;
import com.sarlym.osmanager.domain.service.VeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/veiculo", produces = "application/json")
@Tag(name = "Veiculo", description = "Operações relacionadas aos veículos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final VeiculoMapper veiculoMapper;

    public VeiculoController(VeiculoService veiculoService, VeiculoMapper veiculoMapper) {
        this.veiculoService = veiculoService;
        this.veiculoMapper = veiculoMapper;
    }

    @GetMapping
    public List<VeiculoDTO> listarVeiculos() {
        return veiculoMapper.paraDTOLista(veiculoService.listarVeiculos());
    }

    @GetMapping("/{id}")
    public VeiculoDTO buscarVeiculo(@PathVariable Long id) {
        return veiculoMapper.paraDTO(veiculoService.buscarVeiculoOuErro(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDTO cadastrarVeiculo(@RequestBody VeiculoRequest veiculoRequest) {
        return veiculoMapper.paraDTO(veiculoService.cadastrarVeiculo(veiculoRequest));
    }

    @PutMapping("/{id}")
    public VeiculoDTO alterarVeiculo(@PathVariable Long id, @RequestBody VeiculoRequest veiculoRequest) {
        return veiculoMapper.paraDTO(veiculoService.alterarVeiculo(id, veiculoRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
    }
}
