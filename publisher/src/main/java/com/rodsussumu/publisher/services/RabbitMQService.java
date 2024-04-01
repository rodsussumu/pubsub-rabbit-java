package com.rodsussumu.publisher.services;

public interface RabbitMQService {
    void sendMessage(String queue, Object message);
}
