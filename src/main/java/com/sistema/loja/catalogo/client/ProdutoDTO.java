package com.sistema.loja.catalogo.client;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {}
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(Double preco) { this.preco = preco; }
}