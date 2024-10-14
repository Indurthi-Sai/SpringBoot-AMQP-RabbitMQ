package com.rabbitmq.main.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    /**
     * @param rabbitTemplate
     */
    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @param message
     */
    public void send(String message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Sending message : {}", message);
        }
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);

        LOGGER.info("message sent : {}", message);
    }


}
