package com.sicredi.desafiodigital.exchange.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import static com.sicredi.desafiodigital.constant.QueueConstant.RESULTADO_VOTACAO_QUEUE;
import static com.sicredi.desafiodigital.constant.QueueConstant.VOTACAO_INICIADA_QUEUE;

public class SessaoVotacaoConsumer {

    public static void escutarResultadoDaSessao() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RESULTADO_VOTACAO_QUEUE, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, resultado) -> {
            String message = new String(resultado.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(RESULTADO_VOTACAO_QUEUE, true, deliverCallback, consumerTag -> { });
    }
    
    public static void escutarSessaoIniciada() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(VOTACAO_INICIADA_QUEUE, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, mensagem) -> {
            String message = new String(mensagem.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(VOTACAO_INICIADA_QUEUE, true, deliverCallback, consumerTag -> { });
    }

}
