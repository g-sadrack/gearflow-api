package com.sarlym.osmanager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarlym.osmanager.domain.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
