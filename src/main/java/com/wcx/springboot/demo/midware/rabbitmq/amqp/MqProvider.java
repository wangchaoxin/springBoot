package com.wcx.springboot.demo.midware.rabbitmq.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqProvider {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

//    @Autowired
    public MqProvider(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }

    public AmqpAdmin getAmqpAdmin() {
        return amqpAdmin;
    }

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }
}
