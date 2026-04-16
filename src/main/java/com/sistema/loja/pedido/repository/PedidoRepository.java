package com.sistema.loja.pedido.repository;

import com.sistema.loja.pedido.model.Pedido; // ✅ FALTAVA ISSO

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}