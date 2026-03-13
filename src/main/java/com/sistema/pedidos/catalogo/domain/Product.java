
package com.sistema.pedidos.catalogo.domain;

import java.util.UUID;

/**
 * Entidade que representa um Produto no catálogo.
 * Contém os dados principais do domínio.
 */
public class Product {

    private UUID id;
    private String name;
    private String description;
    private double price;
    private int stock;

    /**
     * Construtor para criar um novo produto.
     */
    public Product(String name, String description, double price, int stock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
