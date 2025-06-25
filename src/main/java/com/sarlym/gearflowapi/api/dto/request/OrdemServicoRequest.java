package com.sarlym.gearflowapi.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoRequest {

    private Long veiculo;
    private Long mecanico;
    private String descricaoProblema;

}
