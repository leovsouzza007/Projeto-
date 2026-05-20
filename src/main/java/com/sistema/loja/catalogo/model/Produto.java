package com.sistema.loja.catalogo.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;

    public Produto() {}

    public Produto(String nome, BigDecimal  preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
