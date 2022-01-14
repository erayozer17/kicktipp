package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.BetForm;
import com.erayozer.kicktipp.model.Team;
import com.erayozer.kicktipp.service.TeamService;
import com.erayozer.kicktipp.worker.QueueWorkerSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.object.SimpleRecordOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

//@RestController
@Controller
@RequestMapping("/try")
public class Try {

    @Autowired
    QueueWorkerSenderService queueWorkerSenderService;

    @Autowired
    TeamService teamService;

    @GetMapping("/frontend")
    public String tryOut() {
        return "index";
    }

    @GetMapping("/registerBets")
    public ModelAndView registerBets(ModelAndView modelAndView) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> teams = teamService.getAll();
        modelAndView.addObject("teams", teams);
        modelAndView.setViewName("registerBet");
        return modelAndView;
    }

    @PostMapping("/doRegisterBets")
    public void foobarPost(@ModelAttribute("bets") BetForm bets, Model model ) {
        System.out.println("jiji");
    }

    @GetMapping("/queue")
    public void tryOutQueue() {
        for (int i = 0 ; i < 20 ; i++){
            Team team = new Team("Turkey", "TUR");
            queueWorkerSenderService.send(team);
        }
    }

    @GetMapping("/registerTeams")
    public void registerTeams() throws ExecutionException, InterruptedException {
        for (int i = 1 ; i < 33 ; i++){
            String name = "team_" + i;
            String abb = "t" + i;
            Team team = new Team(name, abb);
            teamService.save(team);
        }
    }
}
