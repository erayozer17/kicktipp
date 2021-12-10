package com.erayozer.kicktipp.worker;

import com.erayozer.kicktipp.model.Team;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class QueueWorkerConsumerService{

    @RabbitListener(queues = "myQueue")
    public void myQueueListener(Team in) {
        System.out.println("Message read from myQueue : " + in.getName());
    }
}
