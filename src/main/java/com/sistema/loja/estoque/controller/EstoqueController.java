package com.sistema.loja.estoque.controller;

import com.sistema.loja.estoque.model.Estoque;
import com.sistema.loja.estoque.service.EstoqueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public Estoque criar(@RequestParam Long produtoId,
                         @RequestParam Integer quantidade) {
        return service.criar(produtoId, quantidade);
    }

    @PutMapping("/entrada")
    public Estoque entrada(@RequestParam Long produtoId,
                           @RequestParam Integer quantidade) {
        return service.adicionar(produtoId, quantidade);
    }

    @PutMapping("/saida")
    public Estoque saida(@RequestParam Long produtoId,
                         @RequestParam Integer quantidade) {
        return service.remover(produtoId, quantidade);
    }

    @GetMapping("/{produtoId}")
    public Estoque buscar(@PathVariable Long produtoId) {
        return service.buscar(produtoId);
    }
}