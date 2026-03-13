
package com.sistema.pedidos.catalogo.application;

import com.sistema.pedidos.catalogo.domain.Product;
import com.sistema.pedidos.catalogo.infrastructure.ProductRepository;

import java.util.List;
import java.util.UUID;

/**
 * Camada de aplicação responsável pelos casos de uso.
 */
public class ProductService {

    private ProductRepository repository = new ProductRepository();

    /**
     * Cria um produto.
     */
    public void createProduct(Product product) {
        repository.save(product);
    }

    /**
     * Lista produtos.
     */
    public List<Product> listProducts() {
        return repository.findAll();
    }

    /**
     * Deleta produto pelo ID.
     */
    public void deleteProduct(UUID id) {
        repository.delete(id);
    }
}
