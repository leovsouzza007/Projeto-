package com.sistema.loja.catalogo.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Component
public class CatalogoClient {

    private final RestTemplate restTemplate;
    // URL do catálogo — via docker-compose será "http://catalogo-api:8081"
    private final String catalogoUrl = System.getenv()
        .getOrDefault("CATALOGO_URL", "http://localhost:8081");

    public CatalogoClient() {
        this.restTemplate = new RestTemplate();
    }

    public ProdutoDTO buscarProduto(Long id) {
        try {
            ResponseEntity<ProdutoDTO> response = restTemplate
                .getForEntity(catalogoUrl + "/api/v1/produtos/" + id, ProdutoDTO.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Catálogo indisponível: " + e.getMessage());
        }
    }
}