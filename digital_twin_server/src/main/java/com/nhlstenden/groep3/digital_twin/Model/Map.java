/*
package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "maps")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    List<Block> blocks;

    @OneToOne
            @JoinColumn(name = )
    Goals mapGoals;

    AgentVerdict verdict;

    public Map() {}

    public Map(String name, List<Block> blocks, Goals mapGoals, AgentVerdict verdict) {
        this.name = name;
        this.blocks = blocks;
        this.mapGoals = mapGoals;
        this.verdict = verdict;
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

    public Goals getMapGoals() {
        return mapGoals;
    }

    public void setMapGoals(Goals mapGoals) {
        this.mapGoals = mapGoals;
    }

    public AgentVerdict getVerdict() {
        return verdict;
    }

    public void setVerdict(AgentVerdict verdict) {
        this.verdict = verdict;
    }
}
*/
