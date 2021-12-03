package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class TeamService extends FirebaseCrud<Team> {

    public TeamService() throws InstantiationException, IllegalAccessException {
    }

    public String save(Team team) throws InterruptedException, ExecutionException {
        return saveObject(team, team.identifier());
    }

    public Team get(String name) throws InterruptedException, ExecutionException {
        return getObject(name);
    }

    public List<Map<String, Object>> getAll() throws InterruptedException, ExecutionException {
        return getAllObjects();
    }

    public String update(Team team) throws InterruptedException, ExecutionException {
        return updateObject(team, team.identifier());
    }

    public String delete(String name) {
        return deleteObject(name);
    }
}
