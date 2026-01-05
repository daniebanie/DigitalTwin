package com.nhlstenden.groep3.digital_twin.DTO;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class MapDTO {

    private Long id;

    private String name = "default";

    private List<BlockDTO> blocks = new ArrayList<>();

    //private List<Long> blockIds = new ArrayList<>();

    private int livability = 0;

    private int cost = 0;

    private int residents = 0;

    private int workplaces = 0;

    private int parkingSpots = 0;

    private int yield = 0;

    private int greenPercentage = 0;

    private int workplacePercentage = 0;

    public MapDTO() {}

/*    public MapDTO(Long id, String name, List<Long> blockIds, int livability, int cost, int residents, int workplaces, int parkingSpots, int yield, int greenPercentage, int workplacePercentage) {
        this.id = id;
        this.name = name;
        this.blockIds = blockIds;
        this.livability = livability;
        this.cost = cost;
        this.residents = residents;
        this.workplaces = workplaces;
        this.parkingSpots = parkingSpots;
        this.yield = yield;
        this.greenPercentage = greenPercentage;
        this.workplacePercentage = workplacePercentage;
    }*/

    public MapDTO(Long id, String name, List<BlockDTO> blocks, int livability, int cost, int residents, int workplaces, int parkingSpots, int yield, int greenPercentage, int workplacePercentage) {
        this.id = id;
        this.name = name;
        this.blocks = blocks;
        this.livability = livability;
        this.cost = cost;
        this.residents = residents;
        this.workplaces = workplaces;
        this.parkingSpots = parkingSpots;
        this.yield = yield;
        this.greenPercentage = greenPercentage;
        this.workplacePercentage = workplacePercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public List<Long> getBlockIds() {
        return blockIds;
    }

    public void setBlockIds(List<Long> blockIds) {
        this.blockIds = blockIds;
    }*/

    public List<BlockDTO> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockDTO> blocks) {
        this.blocks = blocks;
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

    public int getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(int parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
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
