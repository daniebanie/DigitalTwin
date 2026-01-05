package com.nhlstenden.groep3.digital_twin.DTO;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Polygon;

public class BlockDTO {

    private Long id;

    private BlockType blockType;

    private Long mapId;

/*    //Dit moet nog werkend gemaakt worden
    private Polygon geometry;*/

    private float height;

    private float area;

    private float volume;

    private float calculated_cost;

    private float calculated_yield;

    private int calculated_residents;

    private String description;

    public BlockDTO(){}

    public BlockDTO(Long id, BlockType blockType, Long mapId/*, Polygon geometry*/, float height, float area, float volume, float calculated_cost, float calculated_yield, int calculated_residents, String description) {
        this.id = id;
        this.blockType = blockType;
        this.mapId = mapId;
        //this.geometry = geometry;
        this.height = height;
        this.area = area;
        this.volume = volume;
        this.calculated_cost = calculated_cost;
        this.calculated_yield = calculated_yield;
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

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
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
        return calculated_yield;
    }

    public void setCalculated_yield(float calculated_yield) {
        calculated_yield = calculated_yield;
    }

    public int getCalculated_residents() {
        return calculated_residents;
    }

    public void setCalculated_residents(int calculated_residents) {
        this.calculated_residents = calculated_residents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
