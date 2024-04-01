package com.rodsussumu.subscriber.connections;

import com.rodsussumu.subscriber.constants.RabbitMQConstants;
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
        Queue queuePayment = this.queue(RabbitMQConstants.QUEUE_PAYMENT);
        Queue queueInvoice = this.queue(RabbitMQConstants.QUEUE_INVOICE);

        DirectExchange directExchange = this.directExchange();

        Binding bindingPayment = this.binding(queuePayment, directExchange);
        Binding bindingInvoice = this.binding(queueInvoice, directExchange);

        this.amqpAdmin.declareQueue(queuePayment);
        this.amqpAdmin.declareQueue(queueInvoice);

        this.amqpAdmin.declareExchange(directExchange);

        this.amqpAdmin.declareBinding(bindingInvoice);
        this.amqpAdmin.declareBinding(bindingPayment);
    }

}
