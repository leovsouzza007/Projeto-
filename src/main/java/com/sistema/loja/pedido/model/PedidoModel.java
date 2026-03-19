package com.sistema.loja.pedido.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // EX: CRIADO, PAGO, CANCELADO

    @ElementCollection
    private List<Long> produtosIds; // ids dos produtos

    public Pedido() {}

    public Pedido(String status, List<Long> produtosIds) {
        this.status = status;
        this.produtosIds = produtosIds;
    }

    // getters e setters
}