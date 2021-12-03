package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.Participant;
import com.erayozer.kicktipp.model.Player;
import com.erayozer.kicktipp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public List<Map<String, Object>> getTeams() throws InterruptedException, ExecutionException {
        return playerService.getAll();
    }

    @GetMapping("/{name}")
    public Player getTeam(@PathVariable String name ) throws InterruptedException, ExecutionException{
        return playerService.get(name);
    }

    @PostMapping
    public String createTeam(@RequestBody Player player) throws InterruptedException, ExecutionException {
        return playerService.save(player);
    }

    @PutMapping
    public String updateTeam(@RequestBody Player player) throws InterruptedException, ExecutionException {
        return playerService.update(player);
    }

    @DeleteMapping("/{name}")
    public String deleteTeam(@PathVariable String name){
        return playerService.delete(name);
    }
}
