package com.nhlstenden.groep3.digital_twin.Model;

public class Information {
    private int currentLivability = 0;
    private double currentCost = 0;
    private double currentYield = 0;
    private int currentWorkplaces = 0;
    private int currentResidents = 0;
    private int currentParkingSpots = 0;
    private double currentTotalVolume = 0;
    private double currentTotalArea = 0;
    private double currentGreenPercentage = 0;
    private double currentWorkplacePercentage = 0;

    public Information(int currentLivability, double currentCost, double currentYield, int currentWorkplaces, int currentResidents, int currentParkingSpots, double currentTotalVolume, double currentTotalArea, double currentGreenPercentage, double currentWorkplacePercentage) {
        this.currentLivability = currentLivability;
        this.currentCost = currentCost;
        this.currentYield = currentYield;
        this.currentWorkplaces = currentWorkplaces;
        this.currentResidents = currentResidents;
        this.currentParkingSpots = currentParkingSpots;
        this.currentTotalVolume = currentTotalVolume;
        this.currentTotalArea = currentTotalArea;
        this.currentGreenPercentage = currentGreenPercentage;
        this.currentWorkplacePercentage = currentWorkplacePercentage;
    }

    public Information() {

    }

    public int getCurrentLivability() {
        return currentLivability;
    }

    public void setCurrentLivability(int currentLivability) {
        this.currentLivability = currentLivability;
    }

    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    public double getCurrentYield() {
        return currentYield;
    }

    public void setCurrentYield(double currentYield) {
        this.currentYield = currentYield;
    }

    public int getCurrentWorkplaces() {
        return currentWorkplaces;
    }

    public void setCurrentWorkplaces(int currentWorkplaces) {
        this.currentWorkplaces = currentWorkplaces;
    }

    public int getCurrentResidents() {
        return currentResidents;
    }

    public void setCurrentResidents(int currentResidents) {
        this.currentResidents = currentResidents;
    }

    public int getCurrentParkingSpots() {
        return currentParkingSpots;
    }

    public void setCurrentParkingSpots(int currentParkingSpots) {
        this.currentParkingSpots = currentParkingSpots;
    }

    public double getCurrentTotalVolume() {
        return currentTotalVolume;
    }

    public void setCurrentTotalVolume(double currentTotalVolume) {
        this.currentTotalVolume = currentTotalVolume;
    }

    public double getCurrentTotalArea() {
        return currentTotalArea;
    }

    public void setCurrentTotalArea(double currentTotalArea) {
        this.currentTotalArea = currentTotalArea;
    }

    public double getCurrentGreenPercentage() {
        return currentGreenPercentage;
    }

    public void setCurrentGreenPercentage(double currentGreenPercentage) {
        this.currentGreenPercentage = currentGreenPercentage;
    }

    public double getCurrentWorkplacePercentage() {
        return currentWorkplacePercentage;
    }

    public void setCurrentWorkplacePercentage(double currentWorkplacePercentage) {
        this.currentWorkplacePercentage = currentWorkplacePercentage;
    }
}
