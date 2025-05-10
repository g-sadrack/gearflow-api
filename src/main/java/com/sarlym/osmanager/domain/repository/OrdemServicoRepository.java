package com.sarlym.osmanager.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sarlym.osmanager.domain.model.OrdemServico;

import io.micrometer.common.lang.NonNull;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, OrdemServicoRepositoryQuerys {
    @Query("""
              SELECT os
              FROM OrdemServico os
                JOIN FETCH os.veiculo v
                JOIN FETCH v.proprietario
                JOIN FETCH os.mecanico m
                LEFT JOIN FETCH os.pecas p
                LEFT JOIN FETCH p.peca
              WHERE os.id = :id
            """)
    Optional<OrdemServico> findFullById(@Param("id") Long id);

    @EntityGraph(attributePaths = {
            "veiculo",
            "veiculo.proprietario",
            "mecanico",
            "pecas",
            "pecas.peca",
            "servicos",
            "servicos.servico"
    })
    @Override
    @NonNull
    public Optional<OrdemServico> findById(Long id);

}
