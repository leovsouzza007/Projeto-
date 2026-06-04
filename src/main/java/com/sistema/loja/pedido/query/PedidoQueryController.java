package com.sistema.loja.pedido.query;

import com.sistema.loja.pedido.dto.PedidoReadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller separado para o Read Side — CQRS em ação
// Endpoints de LEITURA não passam pelo PedidoService (Write Side)
@RestController
@RequestMapping("/api/v1/pedidos/read")
public class PedidoQueryController {

    private final PedidoQueryService queryService;

    public PedidoQueryController(PedidoQueryService queryService) {
        this.queryService = queryService;
    }

    // Lista todos do Read Model — dados pré-computados, resposta rápida
    @GetMapping
    public ResponseEntity<List<PedidoReadDTO>> listar() {
        return ResponseEntity.ok(queryService.listarTodos());
    }

    // Busca por ID no Read Model
    @GetMapping("/{id}")
    public ResponseEntity<PedidoReadDTO> buscar(@PathVariable Long id) {
        PedidoReadDTO dto = queryService.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    // Filtra por status — demonstra flexibilidade das queries do Read Side
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoReadDTO>> listarPorStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(queryService.listarPorStatus(status));
    }
}