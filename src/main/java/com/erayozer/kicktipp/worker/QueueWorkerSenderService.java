package com.erayozer.kicktipp.worker;

import com.erayozer.kicktipp.model.Team;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueWorkerSenderService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${kicktipp.rabbitmq.queue}")
    private String queueName;

    public void send(Team team) {
        amqpTemplate.convertAndSend(queueName, team);
    }
}
