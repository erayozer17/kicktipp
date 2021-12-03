package com.erayozer.kicktipp.model;

import java.util.List;

public class Participant implements FirebaseModel{
    private String email;
    private List<Bet> bets;
    private int points;
    private Team champion;
    private Player goalKing;

    public Participant() {}
    public Participant(String email, List<Bet> bets, int points, Team champion, Player goalKing) {
        this.email = email;
        this.bets = bets;
        this.points = points;
        this.champion = champion;
        this.goalKing = goalKing;
    }

    public Team getChampion() {
        return champion;
    }

    public void setChampion(Team champion) {
        this.champion = champion;
    }

    public Player getGoalKing() {
        return goalKing;
    }

    public void setGoalKing(Player goalKing) {
        this.goalKing = goalKing;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    @Override
    public String collectionName() {
        return "participants";
    }

    @Override
    public String identifier() {
        return getEmail();
    }
}
