package com.rabbitmq.main.controller;


import com.rabbitmq.main.dto.User;
import com.rabbitmq.main.publisher.RabbitMQProducer;
import com.rabbitmq.main.publisher.RabbitMqJsonProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    /**
     * <a href="http://localhost:8080/api/v1/publish?message=hello">LOCALHOST PUBLISHER</a>
     * @param message
     * @return
     */
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMQProducer.send(message);
        return ResponseEntity.ok("Message sent successfully");
    }

}
