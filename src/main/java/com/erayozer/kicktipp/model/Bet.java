package com.erayozer.kicktipp.model;

public class Bet extends Result {
    private boolean joker;

    public Bet() {}

    public Bet(boolean joker) {
        this.joker = joker;
    }

    public Bet(boolean joker, Team homeTeam, Team awayTeam, int homeScore, int awayScore, MatchType matchType){
        super(homeTeam, awayTeam, homeScore, awayScore, matchType);
        this.joker = joker;
    }

    public boolean isJoker() {
        return joker;
    }

    public void setJoker(boolean joker) {
        this.joker = joker;
    }
}
