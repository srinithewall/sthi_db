package com.sthi.re.dto.request;

public class ProjectUnitTypeRequest {

    private Integer unitTypeId;
    private Integer sizeSqft;
    private Double priceMin;
    private Double priceMax;
    private String priceUnit;

    // getters & setters
    public Integer getUnitTypeId() { return unitTypeId; }
    public void setUnitTypeId(Integer unitTypeId) { this.unitTypeId = unitTypeId; }

    public Integer getSizeSqft() { return sizeSqft; }
    public void setSizeSqft(Integer sizeSqft) { this.sizeSqft = sizeSqft; }

    public Double getPriceMin() { return priceMin; }
    public void setPriceMin(Double priceMin) { this.priceMin = priceMin; }

    public Double getPriceMax() { return priceMax; }
    public void setPriceMax(Double priceMax) { this.priceMax = priceMax; }

    public String getPriceUnit() { return priceUnit; }
    public void setPriceUnit(String priceUnit) { this.priceUnit = priceUnit; }
}
