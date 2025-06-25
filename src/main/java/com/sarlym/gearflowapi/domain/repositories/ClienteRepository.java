package com.sarlym.gearflowapi.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sarlym.gearflowapi.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
    
    @EntityGraph(attributePaths = {
        "veiculos",
        "veiculos.proprietario"
    })
    @Override
    @NonNull
    Optional<Cliente> findById(@NonNull Long id);

    @EntityGraph(attributePaths = {
        "veiculos",
        "veiculos.proprietario"
    })
    @Override
    @NonNull
    List<Cliente> findAll();
}
