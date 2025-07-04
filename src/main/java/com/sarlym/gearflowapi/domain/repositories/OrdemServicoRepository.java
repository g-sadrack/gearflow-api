package com.sarlym.gearflowapi.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sarlym.gearflowapi.domain.model.OrdemServico;


@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, OrdemServicoRepositoryQuerys {
    @Query("""
              SELECT os
              FROM OrdemServico os
                JOIN FETCH os.veiculo v
                JOIN FETCH v.proprietario
                JOIN FETCH os.mecanico m
                LEFT JOIN FETCH os.produtos p
                LEFT JOIN FETCH p.produto
              WHERE os.id = :id
            """)
    Optional<OrdemServico> findFullById(@Param("id") Long id);

    @EntityGraph(attributePaths = {
            "veiculo",
            "veiculo.proprietario",
            "mecanico",
            "produtos",
            "produtos.produto",
            "servicos",
            "servicos.servico"
    })
    @Override
    @NonNull
    public Optional<OrdemServico> findById(@NonNull Long id);

}
