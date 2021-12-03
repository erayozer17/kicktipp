package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class ParticipantService extends FirebaseCrud<Participant> {
    public ParticipantService() throws InstantiationException, IllegalAccessException {
    }

    @Override
    public String save(Participant participant) throws InterruptedException, ExecutionException {
        return saveObject(participant, participant.identifier());
    }

    @Override
    public Participant get(String name) throws InterruptedException, ExecutionException {
        return getObject(name);
    }

    @Override
    public List<Map<String, Object>> getAll() throws InterruptedException, ExecutionException {
        return getAllObjects();
    }

    @Override
    public String update(Participant participant) throws InterruptedException, ExecutionException {
        return updateObject(participant, participant.identifier());
    }

    @Override
    public String delete(String name) {
        return deleteObject(name);
    }
}
