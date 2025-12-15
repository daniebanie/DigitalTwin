package com.nhlstenden.groep3.digital_twin.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "cost_per_unit", nullable = false, precision = 15, scale = 4)
    private BigDecimal costPerUnit;

    @Column(name = "yield_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal yieldPercentage;

    @Column(name = "residents_per_unit", precision = 10, scale = 4)
    private BigDecimal residentsPerUnit;

    @Column(name = "livability_points", nullable = false)
    private Integer livabilityPoints;

    @Column(name = "color_hex", nullable = false, length = 7)
    private String colorHex;

    @Column(name = "is_volumetric", nullable = false)
    private Boolean isVolumetric;

    @Column(name = "icon_svg", columnDefinition = "TEXT")
    private String iconSvg;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Unit {
        m2, m3
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

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }
    public void setCostPerUnit(BigDecimal costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public BigDecimal getYieldPercentage() {
        return yieldPercentage;
    }
    public void setYieldPercentage(BigDecimal yieldPercentage) {
        this.yieldPercentage = yieldPercentage;
    }

    public BigDecimal getResidentsPerUnit() {
        return residentsPerUnit;
    }
    public void setResidentsPerUnit(BigDecimal residentsPerUnit) {
        this.residentsPerUnit = residentsPerUnit;
    }

    public Integer getLivabilityPoints() {
        return livabilityPoints;
    }
    public void setLivabilityPoints(Integer livabilityPoints) {
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
}

