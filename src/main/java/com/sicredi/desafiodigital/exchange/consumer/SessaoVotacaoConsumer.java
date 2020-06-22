package com.sicredi.desafiodigital.exchange.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class SessaoVotacaoConsumer {

    public static void escutarFilaDaSessaoDeVotacao(String queue, Channel channel) throws Exception {
        channel.queueDeclare(queue, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, resultado) -> {
            String message = new String(resultado.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}
