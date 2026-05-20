package com.sistema.loja.entrega.controller;

import com.sistema.loja.entrega.dto.EntregaRequestDTO;
import com.sistema.loja.entrega.dto.EntregaResponseDTO;
import com.sistema.loja.entrega.service.EntregaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {

    private final EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EntregaResponseDTO> criar(
            @RequestBody EntregaRequestDTO dto) {

        EntregaResponseDTO entrega = service.criarEntrega(dto);

        URI uri = URI.create("/api/v1/entregas/" + entrega.getId());

        return ResponseEntity.created(uri).body(entrega);
    }

    @PutMapping("/{id}/enviar")
    public ResponseEntity<EntregaResponseDTO> enviar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.enviar(id)
        );
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<EntregaResponseDTO> finalizar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.finalizar(id)
        );
    }
}