package com.erayozer.kicktipp.model;

public class Team implements FirebaseModel {

    private String name;

    private int age;

    private String city;

    public Team() {}

    public Team(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String modelColumn() {
        return "teams";
    }

    @Override
    public String identifier() {
        return getName();
    }
}