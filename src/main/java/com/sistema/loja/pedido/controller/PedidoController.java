package com.sistema.loja.pedido.controller;

import com.sistema.loja.pedido.model.Pedido;
import com.sistema.loja.pedido.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    //  Criar pedido
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        Pedido novoPedido = service.criarPedido(pedido);
        return ResponseEntity.status(201).body(novoPedido);
    }

    //  Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    //  Listar todos
    @GetMapping
    public ResponseEntity<Iterable<Pedido>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    //  Atualizar status (ex: CRIADO → PAGO → ENVIADO)
    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id,
                                                  @RequestParam String status) {
        Pedido pedido = service.atualizarStatus(id, status);
        return ResponseEntity.ok(pedido);
    }

    // ✅ Deletar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}