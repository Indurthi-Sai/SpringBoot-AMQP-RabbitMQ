package com.rabbitmq.main.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey.name}")
    private String routingKeyName;

    @Value("${rabbitmq.jsonQueue.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routingKey.json.name}")
    private String jsonRoutingKeyName;

    /**
     * spring bean for RabbitMQ queue
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    /**
     * spring bean to queue to store json message
     * @return
     */
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    /**
     * spring bean for rabbitmq exchange
     * @return
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    /**
     * Binding between queue and exchange
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKeyName);
    }

    /**
     * Binding between json queue and exchange using json routing key
     * @return
     */
    @Bean
    public Binding bindingJson() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKeyName);
    }

    /**
     *
     * @return
     */
    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }

    /**
     * ConnectionFactory
     * RabbitMQTemplate
     * RabbitMQAdmin
     * Above 3 beans spring boot will automatically give us
     **/

}
