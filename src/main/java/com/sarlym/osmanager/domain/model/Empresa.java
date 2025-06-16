package com.sarlym.osmanager.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sarlym.osmanager.api.core.enums.RegimeTributario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empresa {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String cnpj;
    @Column(nullable = false)
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    @Enumerated(EnumType.STRING)
    private RegimeTributario regimeTributario;
    private String cnaePrincipal;
    private String codigoServicoMunicipal;
    private String telefone;
    private String email;
    @Embedded
    private Endereco enderecoFiscal;
    @Lob
    private byte[] logo;
    private String termosPadrao;
    private Boolean exibirValoresParaCliente = Boolean.TRUE;
    private Integer diasValidadeOrcamento = 7;
    private Integer ultimoNumeroNFe = 0;
    // TODO : Implementar certificado digital
    //@Lob
    //@Convert(converter = CryptoConverter.class)
    //private byte[] certificadoDigital; 
    private LocalDate validadeCertificado;
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<OrdemServico> ordensServico = new ArrayList<>();
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Mecanico> mecanicos = new ArrayList<>();

}