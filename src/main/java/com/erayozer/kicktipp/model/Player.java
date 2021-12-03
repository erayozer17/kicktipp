package com.erayozer.kicktipp.model;

public class Player implements FirebaseModel{
    private String name;
    private Team team;

    public Player() {}

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String collectionName() {
        return "players";
    }

    @Override
    public String identifier() {
        return getName();
    }
}
