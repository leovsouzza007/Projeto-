package com.sistema.catalogo.dto;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;

    public ProdutoDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
}