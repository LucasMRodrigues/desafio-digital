package com.sicredi.desafiodigital.exchange.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import static com.sicredi.desafiodigital.exchange.consumer.SessaoVotacaoConsumer.escutarFilaDaSessaoDeVotacao;

public class RabbitMqQueueBuilder {

    public static ConnectionFactory createQueue(String queue) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        escutarFilaDaSessaoDeVotacao(queue, channel);
        return factory;
    }
}
