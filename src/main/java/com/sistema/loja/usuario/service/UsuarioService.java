package com.sistema.loja.usuario.service;

import com.sistema.loja.usuario.model.Usuario;
import com.sistema.loja.usuario.repository.UsuarioRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(
                encoder.encode(usuario.getSenha())
        );

        return repository.save(usuario);
    }
}