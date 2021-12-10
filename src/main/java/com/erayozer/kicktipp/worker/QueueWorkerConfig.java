package com.erayozer.kicktipp.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueWorkerConfig {
    @Value("${kicktipp.rabbitmq.queue}")
    String queueName;

    @Bean
    Queue myQueue() {
        return new Queue(queueName, false);
    }
}
