package com.sistema.loja.estoque.service;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.repository.ProdutoRepository;
import com.sistema.loja.estoque.model.Estoque;
import com.sistema.loja.estoque.repository.EstoqueRepository;

import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository,
                          ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public Estoque criar(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Evita duplicidade
        estoqueRepository.findByProduto(produto).ifPresent(e -> {
            throw new RuntimeException("Estoque já existe para esse produto");
        });

        return estoqueRepository.save(new Estoque(produto, quantidade));
    }

    public Estoque adicionar(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        return estoqueRepository.save(estoque);
    }

    public Estoque remover(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        if (estoque.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        return estoqueRepository.save(estoque);
    }

    public Estoque buscar(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }
}