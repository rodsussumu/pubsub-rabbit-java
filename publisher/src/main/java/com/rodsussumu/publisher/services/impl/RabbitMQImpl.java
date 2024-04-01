package com.rodsussumu.publisher.services.impl;

import com.rodsussumu.publisher.services.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQImpl implements RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queue, Object message) {
        this.rabbitTemplate.convertAndSend(queue, message);
    }

}
