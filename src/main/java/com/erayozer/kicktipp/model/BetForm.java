package com.erayozer.kicktipp.model;

import java.util.List;

public class BetForm {
    private List<Bet> bets;

    public BetForm() {}

    public BetForm(List<Bet> bets) {
        this.bets = bets;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
