package com.sarlym.osmanager.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    private String telefone;
    private String email;
    //@CreationTimestamp
    //private LocalDateTime dataCadastro;
    //@UpdateTimestamp
    //private LocalDateTime dataAtualizacao;

}
