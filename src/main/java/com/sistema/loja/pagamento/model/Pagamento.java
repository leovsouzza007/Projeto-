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

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { // 🔥 ESSE É O IMPORTANTE
        this.status = status;
    }
}