package com.nhlstenden.groep3.digital_twin.Model;

public class Information {
    private int currentLivability = 0;
    private float currentCost = 0;
    private float currentYield = 0;
    private int currentWorkplaces = 0;
    private int currentResidents = 0;
    private int currentParkingSpots = 0;
    private float currentTotalVolume = 0;
    private float currentTotalArea = 0;
    private float currentGreenPercentage = 0;
    private float currentWorkplacePercentage = 0;

    public Information(int currentLivability, float currentCost, float currentYield, int currentWorkplaces, int currentResidents, int currentParkingSpots, float currentTotalVolume, float currentTotalArea, float currentGreenPercentage, float currentWorkplacePercentage) {
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

    public float getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(float currentCost) {
        this.currentCost = currentCost;
    }

    public float getCurrentYield() {
        return currentYield;
    }

    public void setCurrentYield(float currentYield) {
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

    public float getCurrentTotalVolume() {
        return currentTotalVolume;
    }

    public void setCurrentTotalVolume(float currentTotalVolume) {
        this.currentTotalVolume = currentTotalVolume;
    }

    public float getCurrentTotalArea() {
        return currentTotalArea;
    }

    public void setCurrentTotalArea(float currentTotalArea) {
        this.currentTotalArea = currentTotalArea;
    }

    public float getCurrentGreenPercentage() {
        return currentGreenPercentage;
    }

    public void setCurrentGreenPercentage(float currentGreenPercentage) {
        this.currentGreenPercentage = currentGreenPercentage;
    }

    public float getCurrentWorkplacePercentage() {
        return currentWorkplacePercentage;
    }

    public void setCurrentWorkplacePercentage(float currentWorkplacePercentage) {
        this.currentWorkplacePercentage = currentWorkplacePercentage;
    }
}
