package com.nhlstenden.groep3.digital_twin.Model;

public class Type {
    String name;
    String unit;
    boolean commercial;
    int costPerUnit;
    int yieldPercentage;
    int residentsPerUnit;
    int livability;

    public Type(String name, String unit, boolean commercial, int costPerUnit, int yieldPercentage, int residentsPerUnit, int livability) {
        this.name = name;
        this.unit = unit;
        this.commercial = commercial;
        this.costPerUnit = costPerUnit;
        this.yieldPercentage = yieldPercentage;
        this.residentsPerUnit = residentsPerUnit;
        this.livability = livability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public int getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(int costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public int getYieldPercentage() {
        return yieldPercentage;
    }

    public void setYieldPercentage(int yieldPercentage) {
        this.yieldPercentage = yieldPercentage;
    }

    public int getResidentsPerUnit() {
        return residentsPerUnit;
    }

    public void setResidentsPerUnit(int residentsPerUnit) {
        this.residentsPerUnit = residentsPerUnit;
    }

    public int getLivability() {
        return livability;
    }

    public void setLivability(int livability) {
        this.livability = livability;
    }
}
