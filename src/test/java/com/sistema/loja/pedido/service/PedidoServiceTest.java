package com.sistema.loja.pedido.service;

import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.pedido.enums.StatusPedido;
import com.sistema.loja.pedido.model.Pedido;
import com.sistema.loja.pedido.repository.PedidoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoService service;

    @Test
    void deveCriarPedido() {
        Pedido pedido = new Pedido();
        pedido.setProdutosIds(Arrays.asList(1L, 2L));

        // thenAnswer devolve o objeto JÁ modificado pelo service
        when(repository.save(any(Pedido.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Pedido salvo = service.criarPedido(pedido);

        assertNotNull(salvo);
        assertEquals(StatusPedido.CRIADO, salvo.getStatus());
        verify(repository, times(1)).save(any(Pedido.class));
    }

    @Test
    void deveBuscarPedidoPorId() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.CRIADO);

        when(repository.findById(1L))
                .thenReturn(Optional.of(pedido));

        Pedido encontrado = service.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals(StatusPedido.CRIADO, encontrado.getStatus());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoExistir() {
        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveListarTodosPedidos() {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();

        when(repository.findAll())
                .thenReturn(List.of(pedido1, pedido2));

        List<Pedido> pedidos = service.listarTodos();

        assertEquals(2, pedidos.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveAtualizarStatusPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.CRIADO);

        when(repository.findById(1L))
                .thenReturn(Optional.of(pedido));

        // CORREÇÃO PRINCIPAL: thenAnswer para retornar o objeto já com status atualizado
        when(repository.save(any(Pedido.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Pedido atualizado = service.atualizarStatus(1L, StatusPedido.FINALIZADO);

        assertEquals(StatusPedido.FINALIZADO, atualizado.getStatus());
        verify(repository, times(1)).save(any(Pedido.class));
    }

    @Test
    void deveDeletarPedido() {
        when(repository.existsById(1L))
                .thenReturn(true);

        service.deletar(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarPedidoInexistente() {
        when(repository.existsById(99L))
                .thenReturn(false);

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.deletar(99L)
        );
    }
}