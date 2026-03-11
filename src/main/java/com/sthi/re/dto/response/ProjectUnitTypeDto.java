package com.sthi.re.dto.response;

import java.time.LocalDateTime;

public class ProjectUnitTypeDto {

    private Long projectUnitTypeId;
    private Long projectId;
    private Integer unitTypeId;
    private Integer sizeSqft;
    private Double priceMin;
    private Double priceMax;
    private String priceUnit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---------- getters (already present) ----------

    public Long getProjectUnitTypeId() {
        return projectUnitTypeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public Integer getSizeSqft() {
        return sizeSqft;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // ---------- ADD THESE SETTERS ----------

    public void setProjectUnitTypeId(Long projectUnitTypeId) {
        this.projectUnitTypeId = projectUnitTypeId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public void setSizeSqft(Integer sizeSqft) {
        this.sizeSqft = sizeSqft;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
