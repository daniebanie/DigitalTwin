package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//Residents should probably be changed into something like spots or something and some type of value should be added that represents if
//those spots are residential, parking or workplaces

@Entity
@Table(name = "block_types")
public class BlockType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "block_code", nullable = false, unique = true, length = 10)
    private String blockCode;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "resident_type")
    private ResidentType residentType;

    @Column(name = "cost_per_unit", nullable = false)
    private float costPerUnit;

    @Column(name = "yield_percentage", nullable = false)
    private float yieldPercentage;

    @Column(name = "residents_per_unit")
    private float residentsPerUnit;

    @Column(name = "livability_points", nullable = false)
    private int livabilityPoints;

    @Column(name = "color_hex", nullable = false, length = 7)
    private String colorHex;

    @Column(name = "is_volumetric", nullable = false)
    private Boolean isVolumetric;

    @Column(name = "is_green", nullable = false)
    private Boolean isGreen;

    @Column(name = "icon_svg", columnDefinition = "TEXT")
    private String iconSvg;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Unit {
        m2, m3
    }

    public enum ResidentType {
        residential, workplace, parkingSpot
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockCode() {
        return blockCode;
    }
    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getCostPerUnit() {
        return costPerUnit;
    }
    public void setCostPerUnit(float costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public float getYieldPercentage() {
        return yieldPercentage;
    }
    public void setYieldPercentage(float yieldPercentage) {
        this.yieldPercentage = yieldPercentage;
    }

    public float getResidentsPerUnit() {
        return residentsPerUnit;
    }
    public void setResidentsPerUnit(float residentsPerUnit) {
        this.residentsPerUnit = residentsPerUnit;
    }

    public int getLivabilityPoints() {
        return livabilityPoints;
    }
    public void setLivabilityPoints(int livabilityPoints) {
        this.livabilityPoints = livabilityPoints;
    }

    public String getColorHex() {
        return colorHex;
    }
    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Boolean getIsVolumetric() {
        return isVolumetric;
    }
    public void setIsVolumetric(Boolean isVolumetric) {
        this.isVolumetric = isVolumetric;
    }

    public String getIconSvg() {
        return iconSvg;
    }
    public void setIconSvg(String iconSvg) {
        this.iconSvg = iconSvg;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ResidentType getResidentType(){return residentType;}
    public void setResidentType(ResidentType residentType){ this.residentType = residentType; }

    public Boolean getGreen() {
        return isGreen;
    }

    public void setGreen(Boolean green) {
        isGreen = green;
    }
}

