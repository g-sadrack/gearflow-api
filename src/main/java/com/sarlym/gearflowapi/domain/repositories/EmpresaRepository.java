package com.sarlym.gearflowapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarlym.gearflowapi.domain.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
}
