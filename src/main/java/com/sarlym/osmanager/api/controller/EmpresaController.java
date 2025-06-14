package com.sarlym.osmanager.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sarlym.osmanager.api.dto.mapper.EmpresaMapper;
import com.sarlym.osmanager.api.dto.request.EmpresaRequest;
import com.sarlym.osmanager.api.dto.response.EmpresaResumo;
import com.sarlym.osmanager.domain.service.EmpresaService;

@RestController
@RequestMapping(value = "/api/empresas", produces = "application/json")
public class EmpresaController {

    private EmpresaService empresaService;
    private EmpresaMapper empresaMapper;

    public EmpresaController(EmpresaService empresaService, EmpresaMapper empresaMapper) {
        this.empresaService = empresaService;
        this.empresaMapper = empresaMapper;
    }

    @GetMapping("/{id}")
    public EmpresaResumo listarEmpresas(@PathVariable(name = "id") Long id) {
        return empresaMapper.modelParaDTO(empresaService.buscarEmpresaOuErro(id));
    }

    @GetMapping
    public List<EmpresaResumo> listarEmpresas() {
        return empresaMapper.modelListaParaDTOLista(empresaService.listarEmpresas());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaResumo salvar(@RequestBody(required = true) EmpresaRequest empresaRequest) {
        return empresaMapper.modelParaDTO(empresaService.salvar(empresaRequest));
    }

    @PutMapping("/{id}")
    public EmpresaResumo atualizar(@PathVariable(name = "id") Long id,
            @RequestBody(required = true) EmpresaRequest empresaRequest) {
        return empresaMapper.modelParaDTO(empresaService.atualizar(id, empresaRequest));
    }

}
