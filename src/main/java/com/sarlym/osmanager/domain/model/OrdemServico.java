package com.sarlym.osmanager.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sarlym.osmanager.api.core.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_os")
    private String numeroOs;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ABERTA;
    private String descricaoProblema;
    private String diagnosticoTecnico;
    private BigDecimal valorTotal;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataAbertura;
    @UpdateTimestamp
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataFinalizacao;
    private Boolean ativo = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecanico_id")
    private Mecanico mecanico;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<ServicoPrestado> servicos = new ArrayList<>();

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<ProdutoOrdemServico> produtos = new ArrayList<>();

}
