package com.sistema.loja.estoque.repository;

import com.sistema.loja.estoque.model.Estoque;
import com.sistema.loja.catalogo.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProduto(Produto produto);

}