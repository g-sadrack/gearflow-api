package com.sarlym.gearflowapi.api.dto.request;

import com.sarlym.gearflowapi.api.core.enums.RegimeTributario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequest {
    
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private RegimeTributario regimeTributario;
    private String cnaePrincipal;
    private String codigoServicoMunicipal;

}
