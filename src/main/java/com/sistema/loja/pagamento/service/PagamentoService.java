package com.sistema.loja.pagamento.service;

import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.pagamento.enums.StatusPagamento;
import com.sistema.loja.pagamento.model.Pagamento;
import com.sistema.loja.pagamento.repository.PagamentoRepository;

import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    // Criar pagamento
    public Pagamento criarPagamento(Long pedidoId) {

        Pagamento pagamento = new Pagamento();

        pagamento.setPedidoId(pedidoId);
        pagamento.setStatus(StatusPagamento.PENDENTE);

        return repository.save(pagamento);
    }

    // Buscar por ID
    public Pagamento buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Pagamento com ID " + id + " não encontrado"
                        )
                );
    }

    // Aprovar pagamento
    public Pagamento aprovarPagamento(Long id) {

        Pagamento pagamento = buscarPorId(id);

        pagamento.setStatus(StatusPagamento.APROVADO);

        return repository.save(pagamento);
    }

    // Recusar pagamento
    public Pagamento recusarPagamento(Long id) {

        Pagamento pagamento = buscarPorId(id);

        pagamento.setStatus(StatusPagamento.RECUSADO);

        return repository.save(pagamento);
    }
}