package com.sistema.loja.pagamento.service;

import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.pagamento.enums.StatusPagamento;
import com.sistema.loja.pagamento.model.Pagamento;
import com.sistema.loja.pagamento.repository.PagamentoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository repository;

    @InjectMocks
    private PagamentoService service;

    @Test
    void deveCriarPagamento() {

        Pagamento pagamento = new Pagamento();
        pagamento.setPedidoId(1L);
        pagamento.setStatus(StatusPagamento.PENDENTE);

        when(repository.save(any(Pagamento.class)))
                .thenReturn(pagamento);

        Pagamento salvo = service.criarPagamento(1L);

        assertNotNull(salvo);

        assertEquals(
                StatusPagamento.PENDENTE,
                salvo.getStatus()
        );

        verify(repository, times(1))
                .save(any(Pagamento.class));
    }

    @Test
    void deveBuscarPagamentoPorId() {

        Pagamento pagamento = new Pagamento();
        pagamento.setPedidoId(1L);
        pagamento.setStatus(StatusPagamento.APROVADO);

        when(repository.findById(1L))
                .thenReturn(Optional.of(pagamento));

        Pagamento encontrado = service.buscarPorId(1L);

        assertNotNull(encontrado);

        assertEquals(
                StatusPagamento.APROVADO,
                encontrado.getStatus()
        );

        verify(repository, times(1))
                .findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoPagamentoNaoExistir() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveAprovarPagamento() {

        Pagamento pagamento = new Pagamento();
        pagamento.setStatus(StatusPagamento.PENDENTE);

        when(repository.findById(1L))
                .thenReturn(Optional.of(pagamento));

        when(repository.save(any(Pagamento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Pagamento atualizado = service.aprovarPagamento(1L);

        assertEquals(
                StatusPagamento.APROVADO,
                atualizado.getStatus()
        );

        verify(repository, times(1))
                .save(any(Pagamento.class));
    }

    @Test
    void deveRecusarPagamento() {

        Pagamento pagamento = new Pagamento();
        pagamento.setStatus(StatusPagamento.PENDENTE);

        when(repository.findById(1L))
                .thenReturn(Optional.of(pagamento));

        when(repository.save(any(Pagamento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Pagamento atualizado = service.recusarPagamento(1L);

        assertEquals(
                StatusPagamento.RECUSADO,
                atualizado.getStatus()
        );

        verify(repository, times(1))
                .save(any(Pagamento.class));
    }
}