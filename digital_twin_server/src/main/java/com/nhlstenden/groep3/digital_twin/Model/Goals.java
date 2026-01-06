package com.nhlstenden.groep3.digital_twin.Model;

public class Goals {
    int livability;
    int cost;
    int residents;
    int workplaces;
    int parkingSpots;
    int yield;
    int greenPercentage;
    int workplacePercentage;

    public Goals(int livability, int cost, int residents, int workplaces, int yield) {
        this.livability = livability;
        this.cost = cost;
        this.residents = residents;
        this.workplaces = workplaces;
        this.yield = yield;
    }

    public int getLivability() {
        return livability;
    }

    public void setLivability(int livability) {
        this.livability = livability;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public int getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(int workplaces) {
        this.workplaces = workplaces;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }
}
