package com.sarlym.osmanager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarlym.osmanager.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
