package com.sthi.re.model;

import com.sthi.re.enums.PriceUnit;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_project_unit_types")
public class ProjectUnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_unit_type_id")
    private Long projectUnitTypeId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "unit_type_id")
    private Integer unitTypeId;

    @Column(name = "size_sqft")
    private Integer sizeSqft;

    @Column(name = "price_min")
    private Double priceMin;

    @Column(name = "price_max")
    private Double priceMax;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_unit")
    private PriceUnit priceUnit;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getProjectUnitTypeId() {
        return projectUnitTypeId;
    }

    public void setProjectUnitTypeId(Long projectUnitTypeId) {
        this.projectUnitTypeId = projectUnitTypeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public Integer getSizeSqft() {
        return sizeSqft;
    }

    public void setSizeSqft(Integer sizeSqft) {
        this.sizeSqft = sizeSqft;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
