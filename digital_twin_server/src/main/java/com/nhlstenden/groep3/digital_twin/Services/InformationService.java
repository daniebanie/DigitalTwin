package com.nhlstenden.groep3.digital_twin.Services;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Information;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    private Information currentInformation = new Information();
    private final float totalArea = 275000;

    public synchronized Information getCurrentInformation(){
        return currentInformation;
    }

    public void updateValuesFromBlock(Block block){
        currentInformation.setCurrentLivability(currentInformation.getCurrentLivability() + block.getBlockType().getLivabilityPoints());
        currentInformation.setCurrentCost(currentInformation.getCurrentCost() + block.getCalculated_cost());
        currentInformation.setCurrentYield(currentInformation.getCurrentYield() + block.getCalculated_yield());
        currentInformation.setCurrentTotalArea(currentInformation.getCurrentTotalArea()+ block.getArea());

            switch (block.getBlockType().getResidentType()) {
                case BlockType.ResidentType.residential:
                    currentInformation.setCurrentResidents(currentInformation.getCurrentResidents() + block.getCalculated_residents());
                    break;
                case BlockType.ResidentType.workplace:
                    currentInformation.setCurrentWorkplaces(currentInformation.getCurrentWorkplaces() + block.getCalculated_residents());
                    currentInformation.setCurrentWorkplacePercentage((currentInformation.getCurrentWorkplacePercentage() + block.getArea()) / (totalArea / 100));
                    break;
                case BlockType.ResidentType.parkingSpot:
                    currentInformation.setCurrentParkingSpots(currentInformation.getCurrentParkingSpots() + block.getCalculated_residents());
                    break;
                case null:
                    break;
            }

        if (block.getBlockType().getUnit().equals(BlockType.Unit.m3)){
            currentInformation.setCurrentTotalVolume(currentInformation.getCurrentTotalVolume() + block.getVolume());
        }
        if (block.getBlockType().getGreen()){
            System.out.println("Is green is reached " + block.getArea());
            currentInformation.setCurrentGreenPercentage((currentInformation.getCurrentGreenPercentage() + block.getArea()) / (totalArea / 100));
        }
    }

    public void resetCurrentInformation(){
        currentInformation = new Information();
    }
}
