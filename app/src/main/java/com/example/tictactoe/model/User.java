package com.example.tictactoe.model;

public class User {
    private String name;
    private int points;
    private int patidasJugadas;


    public User() {
    }

    public User(String name, int points, int patidasJugadas) {
        this.name = name;
        this.points = points;
        this.patidasJugadas = patidasJugadas;
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

    public int getPatidasJugadas() {
        return patidasJugadas;
    }

    public void setPatidasJugadas(int patidasJugadas) {
        this.patidasJugadas = patidasJugadas;
    }
}
