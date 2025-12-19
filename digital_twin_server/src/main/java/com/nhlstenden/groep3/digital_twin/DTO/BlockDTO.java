package com.nhlstenden.groep3.digital_twin.DTO;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Polygon;

public class BlockDTO {
    //TODO: make mapper for this DTO and try using it instead of Block object in MapDTO



    private Long id;

    private BlockType blockType;

    private Map map;

/*    //Dit moet nog werkend gemaakt worden
    private Polygon geometry;*/

    private float height;

    private float area;

    private float volume;

    private float calculated_cost;

    private float Calculated_yield;

    private float calculated_residents;

    private String description;

    public BlockDTO(Long id, BlockType blockType, Map map/*, Polygon geometry*/, float height, float area, float volume, float calculated_cost, float calculated_yield, float calculated_residents, String description) {
        this.id = id;
        this.blockType = blockType;
        this.map = map;
        //this.geometry = geometry;
        this.height = height;
        this.area = area;
        this.volume = volume;
        this.calculated_cost = calculated_cost;
        Calculated_yield = calculated_yield;
        this.calculated_residents = calculated_residents;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

/*    public Polygon getGeometry() {
        return geometry;
    }

    public void setGeometry(Polygon geometry) {
        this.geometry = geometry;
    }*/

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getCalculated_cost() {
        return calculated_cost;
    }

    public void setCalculated_cost(float calculated_cost) {
        this.calculated_cost = calculated_cost;
    }

    public float getCalculated_yield() {
        return Calculated_yield;
    }

    public void setCalculated_yield(float calculated_yield) {
        Calculated_yield = calculated_yield;
    }

    public float getCalculated_residents() {
        return calculated_residents;
    }

    public void setCalculated_residents(float calculated_residents) {
        this.calculated_residents = calculated_residents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
