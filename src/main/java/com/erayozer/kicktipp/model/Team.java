package com.erayozer.kicktipp.model;

public class Team implements FirebaseModel {

    private String name;

    private int points;

    public Team() {}

    public Team(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String collectionName() {
        return "teams";
    }

    @Override
    public String identifier() {
        return getName();
    }
}