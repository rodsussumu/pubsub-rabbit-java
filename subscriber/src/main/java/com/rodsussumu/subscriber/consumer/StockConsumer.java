package com.rodsussumu.subscriber.consumer;

import constants.RabbitMQConstants;
import dtos.StockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {
    @RabbitListener(queues = RabbitMQConstants.QUEUE_STOCK)
    private void consumer(StockDTO stockDTO) {
        System.out.println("id" + stockDTO.id);
        System.out.println("quantity" + stockDTO.quantity);
        System.out.println("-----------");
    }
}
