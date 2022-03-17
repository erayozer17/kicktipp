package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.BetForm;
import com.erayozer.kicktipp.model.Participant;
import com.erayozer.kicktipp.model.Player;
import com.erayozer.kicktipp.model.Team;
import com.erayozer.kicktipp.service.ParticipantService;
import com.erayozer.kicktipp.service.PlayerService;
import com.erayozer.kicktipp.service.TeamService;
//import com.erayozer.kicktipp.worker.QueueWorkerSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

//@RestController
@Controller
@RequestMapping("/try")
public class Try {

//    @Autowired
//    QueueWorkerSenderService queueWorkerSenderService;

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Autowired
    ParticipantService participantService;

    @GetMapping("/frontend")
    public String tryOut() {
        return "index";
    }

    @GetMapping("/registerBets")
    public ModelAndView registerBets(ModelAndView modelAndView) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> teams = teamService.getAll();
        List<Map<String, Object>> players = playerService.getAll();
        modelAndView.addObject("teams", teams);
        modelAndView.addObject("players", players);
        modelAndView.addObject("bets", new BetForm());
        modelAndView.setViewName("registerBet");
        return modelAndView;
    }

    @PostMapping("/doRegisterBets")
    public ModelAndView foobarPost(@ModelAttribute("bets") BetForm bets, Model model ) throws ExecutionException, InterruptedException {
        Player player = playerService.get(bets.getGoalKing().getName());
        Participant participant = new Participant("denemeform@gg.com", bets.getBets(), 0, bets.getChampion(), player);
        participantService.save(participant);

        // TODO: grup maclarindaki takimlari form sirasina gore burada alman lazim.

        return new ModelAndView("redirect:/try/registerBets");
    }

//    @GetMapping("/queue")
//    public void tryOutQueue() {
//        for (int i = 0 ; i < 20 ; i++){
//            Team team = new Team("Turkey", "TUR");
//            queueWorkerSenderService.send(team);
//        }
//    }

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
