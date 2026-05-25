package com.sistema.loja.pagamento.service;

import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.pagamento.enums.StatusPagamento;
import com.sistema.loja.pagamento.model.Pagamento;
import com.sistema.loja.pagamento.repository.PagamentoRepository;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PagamentoService {

    private static final Logger log = LoggerFactory.getLogger(PagamentoService.class);

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    @CircuitBreaker(name = "pagamento", fallbackMethod = "fallbackCriarPagamento")
    @Retry(name = "pagamento")

    // Criar pagamento
    public Pagamento criarPagamento(Long pedidoId) {

        Pagamento pagamento = new Pagamento();

        pagamento.setPedidoId(pedidoId);
        pagamento.setStatus(StatusPagamento.PENDENTE);

        return repository.save(pagamento);
    }

    // Fallback - executado quando todas as tentativas falham
    public Pagamento fallbackCriarPagamento(Long pedidoId, Exception e) {
    log.error("Fallback ativado para pedido {}. Erro: {}", pedidoId, e.getMessage());
    Pagamento pagamento = new Pagamento();
    pagamento.setPedidoId(pedidoId);
    pagamento.setStatus(StatusPagamento.RECUSADO);
    return pagamento;
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