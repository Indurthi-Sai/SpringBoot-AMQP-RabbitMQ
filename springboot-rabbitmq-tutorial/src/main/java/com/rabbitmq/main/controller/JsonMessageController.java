package com.rabbitmq.main.controller;


import com.rabbitmq.main.dto.User;
import com.rabbitmq.main.publisher.RabbitMqJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {

    private RabbitMqJsonProducer rabbitMqJsonProducer;

    public JsonMessageController(RabbitMqJsonProducer rabbitMqJsonProducer) {
        this.rabbitMqJsonProducer = rabbitMqJsonProducer;
    }

    @PostMapping("/publishJson")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        rabbitMqJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sent successfully");
    }
}
