package com.sistema.loja.catalogo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final CatalogoService service;

    public ProdutoController(CatalogoService service) {
        this.service = service;
    }

    @PostMapping
    public Produto criar(@RequestBody Produto p) {
        return service.salvar(p);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
