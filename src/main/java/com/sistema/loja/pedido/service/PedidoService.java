package com.sistema.loja.pedido.service;

import com.sistema.loja.pedido.model.Pedido;
import com.sistema.loja.pedido.repository.PedidoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    //  Criar pedido
    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus("CRIADO");
        return repository.save(pedido);
    }

    //  Buscar por ID
    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    //  Listar todos
    public List<Pedido> listarTodos() {
        return repository.findAll();
    }
    //  Atualizar status
    public Pedido atualizarStatus(Long id, String status) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(status);
        return repository.save(pedido);
    }

    //  Deletar pedido
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        repository.deleteById(id);
    }
}