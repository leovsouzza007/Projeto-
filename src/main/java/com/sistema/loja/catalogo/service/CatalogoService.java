package com.sistema.loja.catalogo.service;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.repository.ProdutoRepository;
import com.sistema.loja.exception.RecursoNaoEncontradoException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoService {

    private final ProdutoRepository repository;

    public CatalogoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Salvar produto
    public Produto salvar(Produto p) {

        return repository.save(p);
    }

    // Listar produtos
    public List<Produto> listar() {

        return repository.findAll();
    }

    // Deletar produto
    public void deletar(Long id) {

        repository.deleteById(id);
    }

    // Buscar produto por ID
    public Produto buscar(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Produto com ID " + id + " não encontrado"
                        )
                );
    }
}