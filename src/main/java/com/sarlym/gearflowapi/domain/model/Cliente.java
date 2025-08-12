package com.sarlym.gearflowapi.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    @Embedded
    private Endereco endereco;
    @CreationTimestamp
    private LocalDateTime dataCadastro;
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Veiculo> veiculos = new ArrayList<>();

    public void associarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        veiculo.setProprietario(this);
    }
    
    public void desassociarVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
        veiculo.setProprietario(null);
    }
}
