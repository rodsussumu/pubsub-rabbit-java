package com.rodsussumu.publisher.connections;

import com.rodsussumu.publisher.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queue) {
        return new Queue(queue, true, false , false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange directExchange) {
        return new Binding(
                queue.getName(), Binding.DestinationType.QUEUE,
                directExchange.getName(), queue.getName(),
                null
        );
    }

    @PostConstruct
    private void build() {
        Queue queueStock = this.queue(RabbitMQConstants.QUEUE_STOCK);
        Queue queuePrice = this.queue(RabbitMQConstants.QUEUE_PRICE);

        DirectExchange directExchange = this.directExchange();

        Binding bindingStock = this.binding(queueStock, directExchange);
        Binding bindingPrice = this.binding(queuePrice, directExchange);

        this.amqpAdmin.declareQueue(queueStock);
        this.amqpAdmin.declareQueue(queuePrice);

        this.amqpAdmin.declareExchange(directExchange);

        this.amqpAdmin.declareBinding(bindingStock);
        this.amqpAdmin.declareBinding(bindingPrice);
    }

}
