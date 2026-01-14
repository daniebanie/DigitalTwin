package com.nhlstenden.groep3.digital_twin.Model;

public class Goals {
    long mapId;
    int livability;
    int cost;
    int residents;
    int workplaces;
    int parkingSpots;
    int yield;
    int greenPercentage;
    int workplacePercentage;

    public Goals(long mapId, int livability, int cost, int residents, int workplaces, int parkingSpots, int yield, int greenPercentage, int workplacePercentage) {
        this.mapId = mapId;
        this.livability = livability;
        this.cost = cost;
        this.residents = residents;
        this.workplaces = workplaces;
        this.parkingSpots = parkingSpots;
        this.yield = yield;
        this.greenPercentage = greenPercentage;
        this.workplacePercentage = workplacePercentage;
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

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public int getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(int parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public int getGreenPercentage() {
        return greenPercentage;
    }

    public void setGreenPercentage(int greenPercentage) {
        this.greenPercentage = greenPercentage;
    }

    public int getWorkplacePercentage() {
        return workplacePercentage;
    }

    public void setWorkplacePercentage(int workplacePercentage) {
        this.workplacePercentage = workplacePercentage;
    }
}
