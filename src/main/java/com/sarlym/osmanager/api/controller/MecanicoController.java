package com.sarlym.osmanager.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sarlym.osmanager.api.dto.MecanicoDTO;
import com.sarlym.osmanager.api.dto.dtoconverter.MecanicoConverter;
import com.sarlym.osmanager.api.dto.request.MecanicoRequest;
import com.sarlym.osmanager.domain.service.MecanicoService;

@RestController
@RequestMapping("/mecanico")
public class MecanicoController {

    @Autowired
    private MecanicoService mecanicoService;
    @Autowired
    private MecanicoConverter mecanicoConverter;

    @GetMapping("/{id}")
    public MecanicoDTO buscarMecanico(@PathVariable Long id) {
        return mecanicoConverter.paraDTO(mecanicoService.buscarMecanicoOuErro(id));
    }

    @GetMapping
    public List<MecanicoDTO> listarMecanicos() {
        return mecanicoConverter.paraDTOLista(mecanicoService.listarMecanicos());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MecanicoDTO cadastrarMecanico(@RequestBody MecanicoRequest mecanicoRequest) {
        return mecanicoConverter.paraDTO(mecanicoService.cadastrarMecanico(mecanicoRequest));
    }

    @PutMapping("/{id}")
    public MecanicoDTO alterarMecanico(@PathVariable Long id, @RequestBody MecanicoRequest MecanicoRequest) {
        return mecanicoConverter.paraDTO(mecanicoService.atualizarMecanico(id, MecanicoRequest));
    }

    @DeleteMapping("/{id}")
    public void deletarMecanico(@PathVariable Long id) {
        mecanicoService.excluirMecanico(id);
    }
}
