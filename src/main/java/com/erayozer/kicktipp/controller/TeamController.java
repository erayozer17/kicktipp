package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.Team;
import com.erayozer.kicktipp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping
    public List<Map<String, Object>> getTeams() throws InterruptedException, ExecutionException{
        return teamService.getAll();
    }

    @GetMapping("/{name}")
    public Team getTeam(@PathVariable String name ) throws InterruptedException, ExecutionException{
        return teamService.get(name);
    }

    @PostMapping
    public String createTeam(@RequestBody Team team) throws InterruptedException, ExecutionException {
        return teamService.save(team);
    }

    @PutMapping
    public String updateTeam(@RequestBody Team team) throws InterruptedException, ExecutionException {
        return teamService.update(team);
    }

    @DeleteMapping("/{name}")
    public String deleteTeam(@PathVariable String name){
        return teamService.delete(name);
    }
}