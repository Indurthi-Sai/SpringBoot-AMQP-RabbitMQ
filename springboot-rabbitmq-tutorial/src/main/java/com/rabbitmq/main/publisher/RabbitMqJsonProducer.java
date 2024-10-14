package com.rabbitmq.main.publisher;

import com.rabbitmq.main.dto.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitMqJsonProducer {

    private static final Logger LOGGER = Logger.getLogger(RabbitMqJsonProducer.class.getName());

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey.json.name}")
    private String routingJsonKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("Sending message to RabbitMQ %s", user.toString()));
        rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, user);
    }

}
