package com.sistema.catalogo.controller;

import com.sistema.catalogo.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
        // Por ora simulado — depois conecta ao banco real
        ProdutoDTO produto = new ProdutoDTO(id, "Produto " + id, 99.90);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        return ResponseEntity.ok(java.util.List.of(
            new ProdutoDTO(1L, "Notebook", 2500.0),
            new ProdutoDTO(2L, "Mouse", 89.0)
        ));
    }
}