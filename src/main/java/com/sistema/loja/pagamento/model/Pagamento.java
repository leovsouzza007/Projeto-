package com.sistema.loja.pagamento.model;

import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private String status; // APROVADO ou RECUSADO

    public Pagamento() {}

    public Pagamento(Long pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
    }

    // getters e setters
}