package com.sarlym.osmanager.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarlym.osmanager.api.dto.mapper.EmpresaMapper;
import com.sarlym.osmanager.api.dto.request.EmpresaRequest;
import com.sarlym.osmanager.domain.exception.EntidadeNaoEncontradaException;
import com.sarlym.osmanager.domain.model.Empresa;
import com.sarlym.osmanager.domain.repositories.EmpresaRepository;

@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;
    private EmpresaMapper empresaMapper;

    public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    public Empresa buscarEmpresaOuErro(Long id) {
        return empresaRepository.findById(1L)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa nao encontrada"));
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa salvar(EmpresaRequest empresaRequest) {
        Empresa empresa = empresaMapper.modelParaNovo(empresaRequest);
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Long id, EmpresaRequest empresaRequest) {
        Empresa empresa = buscarEmpresaOuErro(id);
        empresaMapper.copiaParaNovo(empresaRequest, empresa);
        return empresaRepository.save(empresa);
    }

}
