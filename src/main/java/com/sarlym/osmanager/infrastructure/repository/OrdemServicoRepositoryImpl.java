package com.sarlym.osmanager.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sarlym.osmanager.api.core.enums.Status;
import com.sarlym.osmanager.domain.model.OrdemServico;
import com.sarlym.osmanager.domain.model.Veiculo;
import com.sarlym.osmanager.domain.repositories.OrdemServicoRepositoryQuerys;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class OrdemServicoRepositoryImpl implements OrdemServicoRepositoryQuerys {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrdemServico> find(String numeroOs, Status status, Long veiculoId, LocalDateTime dataInicio, LocalDateTime dataFim ) {

        // Iniciamos um builder usando o entityManager
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // Define o tipo de retorno, no caso é uma ordemServico, a linha abaixo é similar a um SELECT * FROM ordem_servico;
        CriteriaQuery<OrdemServico> criteria = builder.createQuery(OrdemServico.class);
        // É a raiz da pesquisa, diz em que tabela será a consulta., a linha abaixo é similar a um FROM ordem_servico;
        Root<OrdemServico> root = criteria.from(OrdemServico.class);

         // Carrega Mecanico
        root.fetch("mecanico", JoinType.LEFT);
        // Carrega Veiculo E seu Cliente (proprietario) em uma única consulta
        Fetch<OrdemServico, Veiculo> veiculoFetch = root.fetch("veiculo", JoinType.LEFT);
        veiculoFetch.fetch("proprietario", JoinType.LEFT); // "proprietario" é o campo em Veiculo que referencia Cliente

        // Armazenas os filtros WHERE
        var predicados = new ArrayList<Predicate>();

        // Filtro por número_os (busca parcial com LIKE) WHERE numero_os LIKE '%valor%'
        if (StringUtils.hasText(numeroOs)) {
            predicados.add(builder.like(root.get("numeroOs"), "%" + numeroOs + "%"));
        }
        // Filtro por status (comparação exata) WHERE status = 'ABERTA'
        if (status != null) {
            predicados.add(builder.equal(root.get("status"), status));
        }
        // Filtro por veiculoId (associação exata) WHERE veiculo_id = 5
        if (veiculoId != null) {
            predicados.add(builder.equal(root.get("veiculo").get("id"), veiculoId));
        }
        // Filtro por intervalo de datas(comparação exata) WHERE data_abertura BETWEEN '2024-01-01' AND '2024-01-31'
        if (dataInicio != null && dataFim != null) {
            predicados.add(builder.between(root.get("dataInicio"), dataInicio, dataFim));
        }

        criteria.distinct(true);
        criteria.where(predicados.toArray(new Predicate[0]));
        TypedQuery<OrdemServico> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

}
