package com.sarlym.osmanager.api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sarlym.osmanager.api.dto.request.EmpresaRequest;
import com.sarlym.osmanager.api.dto.response.EmpresaResumo;
import com.sarlym.osmanager.domain.model.Empresa;

@Component
public class EmpresaMapper {

    ModelMapper modelMapper = new ModelMapper();

    public EmpresaResumo modelParaDTO(Empresa empresa) {
        return modelMapper.map(empresa, EmpresaResumo.class);
    }

    public List<EmpresaResumo> modelListaParaDTOLista(List<Empresa> listarEmpresas) {
        return listarEmpresas.stream().map(this::modelParaDTO).toList();
    }

    public Empresa modelParaNovo(EmpresaRequest empresaRequest) {
        return modelMapper.map(empresaRequest, Empresa.class);
    }

    public void copiaParaNovo(EmpresaRequest empresaRequest, Empresa empresa) {
        modelMapper.map(empresaRequest, empresa);
    }

}
