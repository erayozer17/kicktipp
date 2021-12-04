package com.erayozer.kicktipp.model;

import java.util.List;

public class Finalist implements FirebaseModel{
    private FinalistType finalistType;
    private List<Team> teams;

    public Finalist() {}

    public Finalist(FinalistType finalistType, List<Team> teams) {
        this.finalistType = finalistType;
        this.teams = teams;
    }

    public FinalistType getFinalistType() {
        return finalistType;
    }

    public void setFinalistType(FinalistType finalistType) {
        this.finalistType = finalistType;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String collectionName() {
        return "finalists";
    }

    @Override
    public String identifier() {
        return getFinalistType().name();
    }
}
