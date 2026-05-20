package com.sistema.loja.catalogo.controller;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.service.CatalogoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final CatalogoService service;

    // INJEÇÃO VIA CONSTRUTOR
    public ProdutoController(CatalogoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto p) {

        Produto salvo = service.salvar(p);

        URI uri = URI.create("/api/v1/produtos/" + salvo.getId());

        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {

        return ResponseEntity.ok(service.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}