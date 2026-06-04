package com.sistema.loja.pedido.service;

import com.sistema.loja.config.RabbitMQConfig;
import com.sistema.loja.exception.RecursoNaoEncontradoException;
import com.sistema.loja.pedido.enums.StatusPedido;
import com.sistema.loja.pedido.event.PedidoCriadoEvent;
import com.sistema.loja.pedido.model.Pedido;
import com.sistema.loja.pedido.repository.PedidoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PedidoService {

    private static final Logger log =
            LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public PedidoService(PedidoRepository repository,
                         RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.CRIADO);
        Pedido salvo = repository.save(pedido);

        try {
            // Tudo como String — sem tipos complexos que o Jackson não conhece
            PedidoCriadoEvent evento = new PedidoCriadoEvent(
                    salvo.getId(),
                    salvo.getStatus().name(),
                    salvo.getProdutosIds(),
                    LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            );

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    "",
                    evento
            );

            log.info("✅ Pedido {} criado e evento publicado no RabbitMQ",
                    salvo.getId());

        } catch (Exception e) {
            log.error("❌ Falha ao publicar no RabbitMQ: {} — {}",
                    e.getClass().getSimpleName(), e.getMessage());
        }

        return salvo;
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