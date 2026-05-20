package com.sistema.loja.catalogo.service;

import com.sistema.loja.catalogo.model.Produto;
import com.sistema.loja.catalogo.repository.ProdutoRepository;
import com.sistema.loja.exception.RecursoNaoEncontradoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private CatalogoService service;

    @Test
    void deveSalvarProduto() {

        Produto p = new Produto();
        p.setNome("Notebook");
        // p.setPreco(2500.0);  // Isso não é possível, pois o método setPreco espera um BigDecimal
        p.setPreco(BigDecimal.valueOf(2500.0));

        when(repository.save(any())).thenReturn(p);

        Produto salvo = service.salvar(p);

        assertNotNull(salvo);

        verify(repository, times(1)).save(p);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscar(99L)
        );
    }
}