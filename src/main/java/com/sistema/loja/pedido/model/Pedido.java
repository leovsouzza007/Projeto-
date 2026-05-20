package com.sistema.loja.pedido.model;

import com.sistema.loja.pedido.enums.StatusPedido;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ElementCollection
    private List<Long> produtosIds;

    public Pedido() {}

    public Pedido(StatusPedido status, List<Long> produtosIds) {
        this.status = status;
        this.produtosIds = produtosIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public List<Long> getProdutosIds() { return produtosIds; }
    public void setProdutosIds(List<Long> produtosIds) { this.produtosIds = produtosIds; }
}