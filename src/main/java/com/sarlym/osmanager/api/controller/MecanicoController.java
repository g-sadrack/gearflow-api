package com.sarlym.osmanager.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.api.dto.response.MecanicoDTO;
import com.sarlym.osmanager.domain.service.MecanicoService;

@RestController
@RequestMapping("/mecanico")
public class MecanicoController {

    private final MecanicoService mecanicoService;

    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @GetMapping("/{id}")
    public MecanicoDTO buscarMecanico(@PathVariable Long id) {
        return mecanicoService.buscarMecanicoOuErro(id);
    }

    @GetMapping
    public List<MecanicoDTO> listarMecanicos() {
        return mecanicoService.listarMecanicos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MecanicoDTO cadastrarMecanico(@RequestBody MecanicoRequest mecanicoRequest) {
        return mecanicoService.cadastrarMecanico(mecanicoRequest);
    }

    @PutMapping("/{id}")
    public MecanicoDTO alterarMecanico(@PathVariable Long id, @RequestBody MecanicoRequest MecanicoRequest) {
        return mecanicoService.atualizarMecanico(id, MecanicoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMecanico(@PathVariable Long id) {
        mecanicoService.excluirMecanico(id);
    }
}
