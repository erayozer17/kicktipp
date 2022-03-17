package com.erayozer.kicktipp.model;

import java.util.ArrayList;
import java.util.List;

public class BetForm {
    private List<Bet> bets;
    private Team champion;
//    private String homeTeam;
//    private String awayTeam;

    public BetForm() {
        this.bets = new ArrayList<>();
        champion = new Team();
    }

    public BetForm(List<Bet> bets) {
        this.bets = bets;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public Team getChampion() {
        return champion;
    }

    public void setChampion(Team champion) {
        this.champion = champion;
    }

//    public String getHomeTeam() {
//        return homeTeam;
//    }
//
//    public void setHomeTeam(String homeTeam) {
//        this.homeTeam = homeTeam;
//    }
//
//    public String getAwayTeam() {
//        return awayTeam;
//    }
//
//    public void setAwayTeam(String awayTeam) {
//        this.awayTeam = awayTeam;
//    }
}
