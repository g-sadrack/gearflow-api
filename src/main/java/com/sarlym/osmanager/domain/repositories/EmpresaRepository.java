package com.sarlym.osmanager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarlym.osmanager.domain.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
}
