package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.Bet;
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
        if (! validateBets(participant)) {
            return "not_valid";
        }
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

    private boolean validateBets(Participant participant) {
        int numberOfJokers = 0;
        List<Bet> bets = participant.getBets();
        for (Bet bet : bets) {
            // Validate jokers
            if (bet.isJoker()) {
                if (numberOfJokers == 10) {
                    return false;
                }
                numberOfJokers++;
            }

            // Validate teams
            if (bet.getAwayTeam().getAbbreviation().equals(bet.getHomeTeam().getAbbreviation())){
                return false;
            }

            // Validate scores
            if (bet.getHomeScore() < 0 || bet.getAwayScore() < 0) {
                return false;
            }
        }
        return true;
    }
}
