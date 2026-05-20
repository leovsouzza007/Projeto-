package com.sistema.loja.usuario.controller;

import com.sistema.loja.usuario.model.Usuario;
import com.sistema.loja.usuario.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(
            @RequestBody Usuario usuario) {

        return ResponseEntity.ok(
                service.salvar(usuario)
        );
    }
}