package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Finalist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FinalistService extends FirebaseCrud<Finalist> {
    public FinalistService() throws InstantiationException, IllegalAccessException {
    }

    @Override
    public String save(Finalist finalist) throws InterruptedException, ExecutionException {
        return saveObject(finalist, finalist.identifier());
    }

    @Override
    public Finalist get(String name) throws InterruptedException, ExecutionException {
        return getObject(name);
    }

    @Override
    public List<Map<String, Object>> getAll() throws InterruptedException, ExecutionException {
        return getAllObjects();
    }

    @Override
    public String update(Finalist finalist) throws InterruptedException, ExecutionException {
        return updateObject(finalist, finalist.identifier());
    }

    @Override
    public String delete(String name) {
        return deleteObject(name);
    }
}
