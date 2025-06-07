package com.sarlym.osmanager.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoRequest {
    private Long id;
    private String codigo;
    private String descricao;
    private Integer tempo_medio;
}
