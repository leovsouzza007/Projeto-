package com.sistema.loja.catalogo.service;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.repository.ProdutoRepository;
import com.sistema.loja.exception.RecursoNaoEncontradoException;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CatalogoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogoService.class);

    private final ProdutoRepository repository;

    public CatalogoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Salvar produto
    @CacheEvict(value = {"produtos", "produto"}, allEntries = true)
    public Produto salvar(Produto p) {
        log.info("Cache INVALIDADO: salvando produto");
        return repository.save(p);
    }

    // Listar produtos
    @Cacheable(value = "produtos")
    public List<Produto> listar() {
        log.info("Cache MISS: buscando produtos no banco");
        return repository.findAll();
    }

    // Deletar produto
        @CacheEvict(value = {"produtos", "produto"}, allEntries = true)
    public void deletar(Long id) {
        log.info("Cache INVALIDADO: deletando produto {}", id);
        repository.deleteById(id);
    }

    // Buscar produto por ID
        @Cacheable(value = "produto", key = "#id")
    public Produto buscar(Long id) {
        log.info("Cache MISS: buscando produto {} no banco", id);
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Produto com ID " + id + " não encontrado"
                        )
                );
    }
}