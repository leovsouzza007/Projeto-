package com.sistema.loja.estoque.service;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.repository.ProdutoRepository;
import com.sistema.loja.estoque.model.Estoque;
import com.sistema.loja.estoque.repository.EstoqueRepository;

// IMPORTS ADICIONADOS
import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.exception.RegraDeNegocioException;

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
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Produto com ID " + produtoId + " não encontrado"));

        // Evita duplicidade
        estoqueRepository.findByProduto(produto).ifPresent(e -> {
            throw new RegraDeNegocioException(
                    "Estoque já existe para esse produto");
        });

        return estoqueRepository.save(new Estoque(produto, quantidade));
    }

    public Estoque adicionar(Long produtoId, Integer quantidade) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Produto com ID " + produtoId + " não encontrado"));

        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estoque não encontrado"));

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);

        return estoqueRepository.save(estoque);
    }

    public Estoque remover(Long produtoId, Integer quantidade) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Produto com ID " + produtoId + " não encontrado"));

        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estoque não encontrado"));

        // VALIDAÇÃO DE QUANTIDADE
        if (estoque.getQuantidade() < quantidade) {
            throw new RegraDeNegocioException(
                    "Estoque insuficiente");
        }

        estoque.setQuantidade(estoque.getQuantidade() - quantidade);

        return estoqueRepository.save(estoque);
    }

    public Estoque buscar(Long produtoId) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Produto com ID " + produtoId + " não encontrado"));

        return estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estoque não encontrado"));
    }
}