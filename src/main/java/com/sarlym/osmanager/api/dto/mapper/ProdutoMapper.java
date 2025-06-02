package com.sarlym.osmanager.api.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarlym.osmanager.api.dto.request.ProdutoRequest;
import com.sarlym.osmanager.domain.model.Produto;

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

}
