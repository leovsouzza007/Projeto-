package com.sistema.loja.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE       = "pedido-criado-exchange";
    public static final String QUEUE_NOTIF    = "notificacoes.pedido-criado";
    public static final String QUEUE_ESTOQUE  = "estoque.pedido-criado";

    @Bean
    public FanoutExchange pedidoCriadoExchange() {
        return new FanoutExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue queueNotificacoes() {
        return QueueBuilder.durable(QUEUE_NOTIF).build();
    }

    @Bean
    public Queue queueEstoque() {
        return QueueBuilder.durable(QUEUE_ESTOQUE).build();
    }

    @Bean
    public Binding bindingNotificacoes(FanoutExchange pedidoCriadoExchange,
                                       Queue queueNotificacoes) {
        return BindingBuilder.bind(queueNotificacoes).to(pedidoCriadoExchange);
    }

    @Bean
    public Binding bindingEstoque(FanoutExchange pedidoCriadoExchange,
                                  Queue queueEstoque) {
        return BindingBuilder.bind(queueEstoque).to(pedidoCriadoExchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        // ObjectMapper sem módulos de data — só tipos simples
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }                 
}
