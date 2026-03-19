package com.sistema.loja.pedido.service;

import com.sistema.loja.pedido.model.Pedido;
import com.sistema.loja.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus("CRIADO");
        return repository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}