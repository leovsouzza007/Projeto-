package com.sistema.loja.pedido.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ElementCollection
    private List<Long> produtosIds;

    // Construtor vazio (obrigatório pro JPA)
    public Pedido() {}

    // Construtor com parâmetros
    public Pedido(String status, List<Long> produtosIds) {
        this.status = status;
        this.produtosIds = produtosIds;
    }

    // GET ID
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    // GET STATUS
    public String getStatus() {
        return status;
    }

    // SET STATUS
    public void setStatus(String status) {
        this.status = status;
    }

    // GET PRODUTOS
    public List<Long> getProdutosIds() {
        return produtosIds;
    }

    // SET PRODUTOS
    public void setProdutosIds(List<Long> produtosIds) {
        this.produtosIds = produtosIds;
    }
}