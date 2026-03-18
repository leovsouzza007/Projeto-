package com.sistema.loja.catalogo.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CatalogoService {

    private final ProdutoRepository repository;
    private Map<Long, Produto> cache = new HashMap<>();

    public CatalogoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(Produto p) {
        Produto saved = repository.save(p);
        cache.put(saved.getId(), saved);
        return saved;
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
        cache.remove(id);
    }

    public Produto buscar(Long id) {
        if (cache.containsKey(id)) {
            System.out.println("CACHE");
            return cache.get(id);
        }
        Produto p = repository.findById(id).orElse(null);
        if (p != null) cache.put(id, p);
        return p;
    }
}
