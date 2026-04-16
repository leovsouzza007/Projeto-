package com.sistema.loja.entrega.controller;

import com.sistema.loja.entrega.dto.EntregaRequestDTO;
import com.sistema.loja.entrega.dto.EntregaResponseDTO;
import com.sistema.loja.entrega.service.EntregaService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {

    private final EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    @PostMapping
    public EntregaResponseDTO criar(@RequestBody EntregaRequestDTO dto) {
        return service.criarEntrega(dto);
    }

    @PutMapping("/{id}/enviar")
    public EntregaResponseDTO enviar(@PathVariable Long id) {
        return service.enviar(id);
    }

    @PutMapping("/{id}/finalizar")
    public EntregaResponseDTO finalizar(@PathVariable Long id) {
        return service.finalizar(id);
    }
}