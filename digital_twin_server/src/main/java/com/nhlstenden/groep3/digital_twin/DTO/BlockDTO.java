package com.nhlstenden.groep3.digital_twin.DTO;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Polygon;

public class BlockDTO {

    private Long id;

    private BlockType blockType;

    private Long mapId;

    private Polygon geometry;

    private double height;

    private double area;

    private double volume;

    private double calculated_cost;

    private double calculated_yield;

    private int calculated_residents;


    public BlockDTO(){}

    public BlockDTO(Long id, BlockType blockType, Long mapId, Polygon geometry, double height, double area, double volume, double calculated_cost, double calculated_yield, int calculated_residents) {
        this.id = id;
        this.blockType = blockType;
        this.mapId = mapId;
        this.geometry = geometry;
        this.height = height;
        this.area = area;
        this.volume = volume;
        this.calculated_cost = calculated_cost;
        this.calculated_yield = calculated_yield;
        this.calculated_residents = calculated_residents;
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

    public Polygon getGeometry() {
        return geometry;
    }

    public void setGeometry(Polygon geometry) {
        this.geometry = geometry;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCalculated_cost() {
        return calculated_cost;
    }

    public void setCalculated_cost(double calculated_cost) {
        this.calculated_cost = calculated_cost;
    }

    public double getCalculated_yield() {
        return calculated_yield;
    }

    public void setCalculated_yield(double calculated_yield) {
        calculated_yield = calculated_yield;
    }

    public int getCalculated_residents() {
        return calculated_residents;
    }

    public void setCalculated_residents(int calculated_residents) {
        this.calculated_residents = calculated_residents;
    }
}
