package com.sistema.loja.teste;

import com.sistema.loja.pedido.model.Pedido;
import com.sistema.loja.pedido.repository.PedidoRepository;
import com.sistema.loja.pedido.service.PedidoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoService service;

    @Test
    void deveCriarPedido() {
        Pedido pedido = new Pedido();

        when(repository.save(any())).thenReturn(pedido);

        service.criarPedido(pedido);

        assertEquals("CRIADO", pedido.getStatus());
    }

    @Test
    void deveBuscarPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveDarErroQuandoNaoEncontrar() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(1L);
        });
    }
}