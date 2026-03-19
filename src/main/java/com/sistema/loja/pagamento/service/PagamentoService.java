package com.sistema.loja.pagamento.service;

import com.sistema.loja.pagamento.model.Pagamento;
import com.sistema.loja.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento processarPagamento(Pagamento pagamento) {
        pagamento.setStatus("APROVADO"); // simulação
        return repository.save(pagamento);
    }
}