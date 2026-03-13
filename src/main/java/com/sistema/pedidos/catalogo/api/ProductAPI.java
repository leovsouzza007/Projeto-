
package com.sistema.pedidos.catalogo.api;

import com.sistema.pedidos.catalogo.application.ProductService;
import com.sistema.pedidos.catalogo.domain.Product;

import java.util.List;
import java.util.UUID;

/**
 * Camada de API que simula endpoints REST.
 */
public class ProductAPI {

    private ProductService service = new ProductService();

    /**
     * Endpoint para criar produto.
     */
    public void createProduct(Product product) {
        service.createProduct(product);
    }

    /**
     * Endpoint para listar produtos.
     */
    public List<Product> listProducts() {
        return service.listProducts();
    }

    /**
     * Endpoint para deletar produto.
     */
    public void deleteProduct(UUID id) {
        service.deleteProduct(id);
    }
}
