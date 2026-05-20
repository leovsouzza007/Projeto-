package com.sistema.loja.estoque.controller;

import com.sistema.loja.estoque.model.Estoque;
import com.sistema.loja.estoque.service.EstoqueService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/estoques")

public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Estoque> criar(@RequestParam Long produtoId,
                                         @RequestParam Integer quantidade) {

        Estoque estoque = service.criar(produtoId, quantidade);

        URI uri = URI.create("/api/v1/estoques/" + produtoId);

        return ResponseEntity.created(uri).body(estoque);
    }

    @PutMapping("/entrada")
    public ResponseEntity<Estoque> entrada(@RequestParam Long produtoId,
                                           @RequestParam Integer quantidade) {

        return ResponseEntity.ok(
                service.adicionar(produtoId, quantidade)
        );
    }

    @PutMapping("/saida")
    public ResponseEntity<Estoque> saida(@RequestParam Long produtoId,
                                         @RequestParam Integer quantidade) {

        return ResponseEntity.ok(
                service.remover(produtoId, quantidade)
        );
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Estoque> buscar(@PathVariable Long produtoId) {

        return ResponseEntity.ok(
                service.buscar(produtoId)
        );
    }
}