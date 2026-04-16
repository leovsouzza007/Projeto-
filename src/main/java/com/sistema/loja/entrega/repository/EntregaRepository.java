package com.sistema.loja.entrega.repository;

import com.sistema.loja.entrega.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}