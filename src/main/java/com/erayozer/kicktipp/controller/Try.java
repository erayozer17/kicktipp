package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.Team;
import com.erayozer.kicktipp.worker.QueueWorkerSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/try")
public class Try {

    @Autowired
    QueueWorkerSenderService queueWorkerSenderService;

    @GetMapping("/frontend")
    public String tryOut() {
        return "index";
    }

    @GetMapping("/queue")
    public void tryOutQueue() {
        for (int i = 0 ; i < 20 ; i++){
            Team team = new Team("Turkey", "TUR");
            queueWorkerSenderService.send(team);
        }
    }
}
