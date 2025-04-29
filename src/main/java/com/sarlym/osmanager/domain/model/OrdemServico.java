package com.sarlym.osmanager.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sarlym.osmanager.api.core.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero_os;
    private Status status;
    private String descricaoProblema;
    private String diagnosticoTecnico;
    private BigDecimal valorTotal;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataAbertura;
    @UpdateTimestamp
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataFinalizacao;
       
}
