package com.sarlym.gearflowapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sarlym.gearflowapi.api.core.enums.StatusEstoque;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false, unique = true)
    private Produto produto;
    private Integer quantidadeAtual;
    private Integer quantidadeReserva;
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;
    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false, unique = true)
    private Fornecedor fornecedor;
    private BigDecimal custoMedio;
    private LocalDateTime dataUltimaEntrada;
    private LocalDateTime dataUltimaSaida;
    private String localizacao;
    @OneToOne
    @JoinColumn(name = "empresa_id", nullable = false, unique = true)
    private Empresa empresa;
    @Enumerated(EnumType.STRING)
    private StatusEstoque StatusEstoque;
    private String lote;
    @CreationTimestamp
    private LocalDateTime dataCadastro;
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
    private String descricao;

}
