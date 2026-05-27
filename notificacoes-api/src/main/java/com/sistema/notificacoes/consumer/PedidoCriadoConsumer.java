package com.sistema.notificacoes.consumer;

import com.sistema.notificacoes.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PedidoCriadoConsumer {

    private static final Logger log = LoggerFactory.getLogger(PedidoCriadoConsumer.class);

    // @RabbitListener = equivalente ao IConsumer<T> do MassTransit
    // Escuta a fila e processa cada mensagem que chega
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NOTIFICACOES)
    public void consumir(Map<String, Object> evento) {

        Long pedidoId = ((Number) evento.get("pedidoId")).longValue();
        String status = (String) evento.get("status");

        // Equivalente ao LogEmailService do .NET — simula envio de e-mail
        log.info("=========================================");
        log.info("[E-MAIL SIMULADO] Pedido recebido!");
        log.info("  Pedido ID : {}", pedidoId);
        log.info("  Status    : {}", status);
        log.info("  Mensagem  : Seu pedido foi criado com sucesso!");
        log.info("=========================================");
    }
}