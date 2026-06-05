package com.sistema.loja.pedido.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PedidoNotificacaoController {

    private final SimpMessagingTemplate messagingTemplate;

    public PedidoNotificacaoController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Envia notificação para todos os clientes conectados
    public void notificarMudancaStatus(Long pedidoId, String novoStatus) {
        messagingTemplate.convertAndSend(
                "/topic/pedidos/" + pedidoId,
                new StatusNotificacao(pedidoId, novoStatus)
        );
    }

    // Record para a mensagem de notificação
    public record StatusNotificacao(Long pedidoId, String status) {}
}