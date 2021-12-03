package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PlayerService extends FirebaseCrud<Player>{
    public PlayerService() throws InstantiationException, IllegalAccessException {
    }

    @Override
    public String save(Player player) throws InterruptedException, ExecutionException {
        return saveObject(player, player.identifier());
    }

    @Override
    public Player get(String name) throws InterruptedException, ExecutionException {
        return getObject(name);
    }

    @Override
    public List<Map<String, Object>> getAll() throws InterruptedException, ExecutionException {
        return getAllObjects();
    }

    @Override
    public String update(Player player) throws InterruptedException, ExecutionException {
        return updateObject(player, player.identifier());
    }

    @Override
    public String delete(String name) {
        return deleteObject(name);
    }
}
