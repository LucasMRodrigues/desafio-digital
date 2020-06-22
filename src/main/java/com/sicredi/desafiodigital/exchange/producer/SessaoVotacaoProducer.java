package com.sicredi.desafiodigital.exchange.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

import static com.sicredi.desafiodigital.constant.QueueConstant.*;

public class SessaoVotacaoProducer {

    public static void publicarResultadoDaSessao(Integer codigoSessaoVotacao) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                channel.queueDeclare(RESULTADO_VOTACAO_QUEUE, false, false, false, null);
                String message = "Sessão " + codigoSessaoVotacao + " encerrada!";
                channel.basicPublish("", RESULTADO_VOTACAO_QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static void publicarInicioDaSessao(Integer codigoSessaoVotacao) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(VOTACAO_INICIADA_QUEUE, false, false, false, null);
            String message = "Sessão " + codigoSessaoVotacao + " está aberta para votação!";
            channel.basicPublish("", VOTACAO_INICIADA_QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}