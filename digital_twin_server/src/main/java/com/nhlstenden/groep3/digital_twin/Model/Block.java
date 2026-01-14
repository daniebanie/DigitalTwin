package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "type_id")
    private BlockType blockType;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private Map map;

    @Column(nullable = false, columnDefinition = "geometry")
    private Polygon geometry;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false, name = "area_m2")
    private double area;

    @Column(nullable = false, name = "volume_m3")
    private double volume;

    @Column(nullable = false)
    private double calculated_cost;

    @Column(nullable = false)
    private double calculated_yield;

    @Column(nullable = false)
    private int calculated_residents;



    public Block() {}

    public Block(BlockType blockType, Map map, Polygon geometry, double height, double area, double volume, double calculated_cost, double calculated_yield, int calculated_residents) {
        this.blockType = blockType;
        this.map = map;
        this.geometry = geometry;
        this.height = height;
        this.area = area;
        this.volume = volume;
        this.calculated_cost = calculated_cost;
        this.calculated_yield = calculated_yield;
        this.calculated_residents = calculated_residents;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
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
        this.calculated_yield = calculated_yield;
    }

    public int getCalculated_residents() {
        return calculated_residents;
    }

    public void setCalculated_residents(int calculated_residents) {
        this.calculated_residents = calculated_residents;
    }

}
