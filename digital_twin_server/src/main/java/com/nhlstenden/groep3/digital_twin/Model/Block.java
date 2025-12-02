package com.nhlstenden.groep3.digital_twin.Model;

public class Block {
    int id;
    Type type;
    String description;
    String dimensions;
    int area;
    int cost;
    int yield;
    int residents;

    public Block(int id, Type type, String description, String dimensions, int area, int cost, int yield, int residents) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.dimensions = dimensions;
        this.area = area;
        this.cost = cost;
        this.yield = yield;
        this.residents = residents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }
}
