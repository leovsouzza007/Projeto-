package com.sistema.loja.pagamento.controller;

import com.sistema.loja.pagamento.model.Pagamento;
import com.sistema.loja.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public Pagamento pagar(@RequestBody Pagamento pagamento) {
        return service.processarPagamento(pagamento);
    }
}