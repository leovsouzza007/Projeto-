package com.sistema.loja.estoque.service;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.repository.ProdutoRepository;
import com.sistema.loja.estoque.model.Estoque;
import com.sistema.loja.estoque.repository.EstoqueRepository;
import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.exception.RegraDeNegocioException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstoqueServiceTest {

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private EstoqueService service;

    @Test
    void deveCriarEstoque() {

        Produto produto = new Produto();
        

        Estoque estoque = new Estoque(produto, 10);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(estoqueRepository.findByProduto(produto))
                .thenReturn(Optional.empty());

        when(estoqueRepository.save(any()))
                .thenReturn(estoque);

        Estoque salvo = service.criar(1L, 10);

        assertNotNull(salvo);

        verify(estoqueRepository, times(1))
                .save(any());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {

        when(produtoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.criar(99L, 10)
        );
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueJaExistir() {

        Produto produto = new Produto();

        Estoque estoque = new Estoque(produto, 10);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(estoqueRepository.findByProduto(produto))
                .thenReturn(Optional.of(estoque));

        assertThrows(
                RegraDeNegocioException.class,
                () -> service.criar(1L, 10)
        );
    }

    @Test
    void deveAdicionarQuantidadeAoEstoque() {

        Produto produto = new Produto();

        Estoque estoque = new Estoque(produto, 10);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(estoqueRepository.findByProduto(produto))
                .thenReturn(Optional.of(estoque));

        when(estoqueRepository.save(any()))
                .thenReturn(estoque);

        Estoque atualizado = service.adicionar(1L, 5);

        assertNotNull(atualizado);

        verify(estoqueRepository, times(1))
                .save(any());
    }

    @Test
    void deveRemoverQuantidadeDoEstoque() {

        Produto produto = new Produto();

        Estoque estoque = new Estoque(produto, 20);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(estoqueRepository.findByProduto(produto))
                .thenReturn(Optional.of(estoque));

        when(estoqueRepository.save(any()))
                .thenReturn(estoque);

        Estoque atualizado = service.remover(1L, 5);

        assertNotNull(atualizado);

        verify(estoqueRepository, times(1))
                .save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueForInsuficiente() {

        Produto produto = new Produto();

        Estoque estoque = new Estoque(produto, 2);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        when(estoqueRepository.findByProduto(produto))
                .thenReturn(Optional.of(estoque));

        assertThrows(
                RegraDeNegocioException.class,
                () -> service.remover(1L, 10)
        );
    }
}