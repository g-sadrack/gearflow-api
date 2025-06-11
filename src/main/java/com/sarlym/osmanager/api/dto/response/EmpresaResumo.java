package com.sarlym.osmanager.api.dto.response;

import java.time.LocalDate;

import com.sarlym.osmanager.api.core.enums.RegimeTributario;
import com.sarlym.osmanager.domain.model.Endereco;

import lombok.Data;

@Data
public class EmpresaResumo {

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private RegimeTributario regimeTributario;
    private String cnaePrincipal;
    private String codigoServicoMunicipal;
    private String telefone;
    private String email;
    private Endereco enderecoFiscal;
    private byte[] logo;
    private String termosPadrao;
    private Boolean exibirValoresParaCliente;
    private Integer diasValidadeOrcamento;
    private Integer ultimoNumeroNFe;
    // TODO : Implementar certificado digital
    // @Lob
    // @Convert(converter = CryptoConverter.class)
    // private byte[] certificadoDigital;
    private LocalDate validadeCertificado;
    

}
