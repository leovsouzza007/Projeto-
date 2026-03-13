
package com.sistema.pedidos;

import com.sistema.pedidos.catalogo.api.ProductAPI;
import com.sistema.pedidos.catalogo.domain.Product;

/**
 * Classe principal para executar o sistema.
 */
public class Main {

    public static void main(String[] args) {

        ProductAPI api = new ProductAPI();

        Product p1 = new Product("Notebook", "Notebook Gamer", 4500.0, 5);
        Product p2 = new Product("Mouse", "Mouse Gamer", 150.0, 10);

        api.createProduct(p1);
        api.createProduct(p2);

        System.out.println("Lista de produtos:");

        api.listProducts().forEach(product ->
                System.out.println(product.getName() + " - R$ " + product.getPrice())
        );

        api.deleteProduct(p1.getId());

        System.out.println("\nApós deletar um produto:");

        api.listProducts().forEach(product ->
                System.out.println(product.getName() + " - R$ " + product.getPrice())
        );
    }
}
