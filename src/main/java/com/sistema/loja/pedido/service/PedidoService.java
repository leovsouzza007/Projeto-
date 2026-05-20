package com.sistema.loja.pedido.service;

import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.pedido.enums.StatusPedido;
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

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.CRIADO);
        return repository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido com ID " + id + " não encontrado"));
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public Pedido atualizarStatus(Long id, StatusPedido status) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(status);
        return repository.save(pedido);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Pedido com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }
}