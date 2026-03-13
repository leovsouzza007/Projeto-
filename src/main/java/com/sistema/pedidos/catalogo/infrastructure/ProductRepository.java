
package com.sistema.pedidos.catalogo.infrastructure;

import com.sistema.pedidos.catalogo.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Camada de infraestrutura responsável por armazenar produtos.
 * Neste exemplo usamos uma lista simulando um banco de dados.
 */
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    /**
     * Salva um produto.
     */
    public void save(Product product) {
        products.add(product);
    }

    /**
     * Retorna todos os produtos.
     */
    public List<Product> findAll() {
        return products;
    }

    /**
     * Remove um produto pelo ID.
     */
    public void delete(UUID id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
