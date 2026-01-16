package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maps")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name = "default";

    @OneToMany(mappedBy = "map")
    private List<Block> blocks = new ArrayList<>();

    @Column(nullable = false)
    private int livability = 100;

    @Column(nullable = false)
    private int cost = 1000000;

    @Column(nullable = false)
    private int residents = 3000;

    @Column(nullable = false)
    private int workplaces = 500;

    @Column(name = "parking_spots", nullable = false)
    private int parkingSpots = 4500;

    @Column(nullable = false)
    private int yield = 100000;

    @Column(name = "green_percentage", nullable = false)
    private int greenPercentage = 20;

    @Column(name = "workplace_percentage", nullable = false)
    private int workplacePercentage = 20;

/*    @OneToOne
    @JoinColumn(name = "verdicts")
    private AgentVerdict verdict;*/

    public Map() {}

    public Map(String name, List<Block> blocks, int livability, int cost, int residents, int workplaces, int parkingSpots, int yield, int greenPercentage, int workplacePercentage) {
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

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(Block block){
        blocks.add(block);
    }

    public void removeBlock(int id){
        blocks.removeIf(block -> block.getId() == id);
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

/*    public AgentVerdict getVerdict() {
        return verdict;
    }

    public void setVerdict(AgentVerdict verdict) {
        this.verdict = verdict;
    }*/
}
