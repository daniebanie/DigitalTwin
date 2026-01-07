package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Coordinate;
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

/*    //Dit moet nog werkend gemaakt worden
    @Column(nullable = false)
    private Polygon geometry;*/

    @Column(nullable = false)
    private float height;

    @Column(nullable = false, name = "area_m2")
    private float area;

    @Column(nullable = false, name = "volume_m3")
    private float volume;

    @Column(nullable = false)
    private float calculated_cost;

    @Column(nullable = false)
    private float calculated_yield;

    @Column(nullable = false)
    private int calculated_residents;

    @Column(nullable = false)
    private String description;


    public Block() {}

    public Block(BlockType blockType, Map map, /*Polygon geometry,*/ float height, float area, float volume, float calculated_cost, float calculated_yield, int calculated_residents, String description) {
        this.blockType = blockType;
        this.map = map;
        //this.geometry = geometry;
        this.height = height;
        this.area = area;
        this.volume = volume;
        this.calculated_cost = calculated_cost;
        this.calculated_yield = calculated_yield;
        this.calculated_residents = calculated_residents;
        this.description = description;
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
        this.calculated_yield = calculated_yield;
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
