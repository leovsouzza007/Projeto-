package com.sistema.loja.pagamento.controller;

import com.sistema.loja.pagamento.model.Pagamento;
import com.sistema.loja.pagamento.service.PagamentoService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping("/{pedidoId}")
    public Pagamento criarPagamento(@PathVariable Long pedidoId) {

        return service.criarPagamento(pedidoId);
    }
}