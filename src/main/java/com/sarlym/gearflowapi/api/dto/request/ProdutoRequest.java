package com.sarlym.gearflowapi.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    private Long id;
    private String codigo;
    private String nome;

}
