package com.sarlym.gearflowapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    @Column(name = "endereco_numero")
    private String numero;
    @Column(name = "endereco_bairro")
    private String bairro;
    @Column(name = "endereco_cep")
    private String cep;
    @Column(name = "endereco_logradouro")
    private String logradouro;
    @Column(name = "endereco_complemento")
    private String complemento;
    @Column(name = "endereco_cidade")
    private String cidade;
    @Column(name = "endereco_estado")
    private String estado;
    @Column(name = "endereco_uf")
    private String uf;
    @Column(name = "endereco_codigo_cidade_ibge")
    private String codigoCidadeIBGE;

}
