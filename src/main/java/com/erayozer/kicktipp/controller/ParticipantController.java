package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.Participant;
import com.erayozer.kicktipp.model.Team;
import com.erayozer.kicktipp.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @GetMapping
    public List<Map<String, Object>> getTeams() throws InterruptedException, ExecutionException {
        return participantService.getAll();
    }

    @GetMapping("/{email}")
    public Participant getTeam(@PathVariable String email ) throws InterruptedException, ExecutionException{
        return participantService.get(email);
    }

    @PostMapping
    public String createTeam(@RequestBody Participant participant) throws InterruptedException, ExecutionException {
        return participantService.save(participant);
    }

    @PutMapping
    public String updateTeam(@RequestBody Participant participant) throws InterruptedException, ExecutionException {
        return participantService.update(participant);
    }

    @DeleteMapping("/{email}")
    public String deleteTeam(@PathVariable String email){
        return participantService.delete(email);
    }
}
