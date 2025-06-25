package com.sarlym.gearflowapi.api.dto.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.gearflowapi.api.dto.request.ProdutoRequest;
import com.sarlym.gearflowapi.api.dto.response.ProdutoDTO;
import com.sarlym.gearflowapi.domain.model.Produto;

@Component
public class ProdutoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Produto requestParaModelo(ProdutoRequest request) {
       return modelMapper.map(request, Produto.class);
    }

    public void copiaParaNovo(ProdutoRequest request, Produto produto) {
        modelMapper.map(request, produto);
    }

    public ProdutoDTO modelParaDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> modeloParaDTOLista(List<Produto> produtos) {
        return produtos.stream().map(this::modelParaDTO).toList();
    }

}
