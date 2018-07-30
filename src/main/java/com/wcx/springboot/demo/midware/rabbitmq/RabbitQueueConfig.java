package com.wcx.springboot.demo.midware.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 需要先将queue建立出来,设置不同的queue name
 */
@Configuration
public class RabbitQueueConfig {


    @Bean
    public Queue queue() {
        Queue queue = new Queue("hello");
        return queue;
    }
    @Bean
    public Queue user() {
        Queue queue = new Queue("user");
        return queue;
    }
}
