package com.nhlstenden.groep3.digital_twin.Controller;


import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/info")
public class InformationController {
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

    public void updateValuesFromBlock(Block block){
        currentLivability = currentLivability + block.getBlockType().getLivabilityPoints();
        currentCost = currentCost + block.getCalculated_cost();
        currentYield = currentYield + block.getCalculated_yield();
        currentTotalArea = currentTotalArea + block.getArea();
        switch (block.getBlockType().getBlockCode()){
            case "A", "B", "C":
                currentResidents = currentResidents + block.getCalculated_residents();
                break;
            case "D":
                currentWorkplaces = currentWorkplaces + block.getCalculated_residents();
                currentWorkplacePercentage = currentWorkplacePercentage + block.getArea();
                break;
            case "G", "H":
                currentParkingSpots = currentParkingSpots + block.getCalculated_residents();
                break;
        }
        if (block.getBlockType().getUnit().equals(BlockType.Unit.m3)){
            currentTotalVolume = currentTotalVolume + block.getVolume();
        }
        if (block.getBlockType().getBlockCode().equals("E") || block.getBlockType().getBlockCode().equals("H")){
            currentGreenPercentage = currentGreenPercentage + block.getArea();
        }
    }

    public int getCurrentLivability() {
        return currentLivability;
    }

    public float getCurrentCost() {
        return currentCost;
    }

    public float getCurrentYield() {
        return  currentYield;
    }

    public int getCurrentWorkplaces() {
        return currentWorkplaces;
    }

    public int getCurrentResidents() {
        return currentResidents;
    }

    public int getCurrentParkingSpots() {
        return currentParkingSpots;
    }

    public float getCurrentTotalVolume() {
        return currentTotalVolume;
    }

    public float getCurrentTotalArea() {
        return currentTotalArea;
    }

    public float getCurrentGreenPercentage() {
        return currentGreenPercentage;
    }

    public float getCurrentWorkplacePercentage() {
        return currentWorkplacePercentage;
    }
}
