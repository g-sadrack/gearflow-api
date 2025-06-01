package com.sarlym.osmanager.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sarlym.osmanager.api.core.enums.TipoFornecedor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String contatoPrincipal;
    private String telefoneContato;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
    private String site;
    @Enumerated(EnumType.STRING)
    private TipoFornecedor tipoFornecedor;
    private String condicaoPagamento;
    private Integer prazoEntrega;
    private BigDecimal descontoPadrao;
    @CreationTimestamp
    private LocalDateTime dataCadastro;
    @UpdateTimestamp
    private LocalDateTime dataUltimaAtualizacao;
    private Boolean ativo = true;

}
