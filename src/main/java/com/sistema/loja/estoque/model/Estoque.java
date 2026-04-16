package com.sistema.loja.estoque.model;

import com.sistema.loja.catalogo.model.Produto;
import jakarta.persistence.*;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id", unique = true)
    private Produto produto;

    private Integer quantidade;

    public Estoque() {}

    public Estoque(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() { return quantidade; }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}