package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.Finalist;
import com.erayozer.kicktipp.service.FinalistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/finalist")
public class FinalistController {

    @Autowired
    FinalistService finalistService;

    @GetMapping
    public List<Map<String, Object>> getTeams() throws InterruptedException, ExecutionException {
        return finalistService.getAll();
    }

    @GetMapping("/{name}")
    public Finalist getTeam(@PathVariable String name ) throws InterruptedException, ExecutionException{
        return finalistService.get(name);
    }

    @PostMapping
    public String createTeam(@RequestBody Finalist finalist) throws InterruptedException, ExecutionException {
        return finalistService.save(finalist);
    }

    @PutMapping
    public String updateTeam(@RequestBody Finalist finalist) throws InterruptedException, ExecutionException {
        return finalistService.update(finalist);
    }

    @DeleteMapping("/{name}")
    public String deleteTeam(@PathVariable String name){
        return finalistService.delete(name);
    }
}
