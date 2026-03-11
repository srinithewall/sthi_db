package com.sthi.re.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyCardResponse {

    private Long propertyId;

    // Location
    private String city;
    private String area;
    private String subArea; // ✅ NEW
    private String zone; // ✅ NEW

    // Basic Details
    private Integer unitTypeId;
    private Double sizeSqft;

    private Double price;
    private String priceUnit;

    private String propertyName; // ✅ NEW

    private LocalDateTime createdAt;

    // Cover Image
    private String coverImageUrl;

    // Tags
    private List<String> tags; // ✅ NEW (populated in service, not query)

    // ============================
    // Constructor for JPQL (12 params — no tags, tags fetched separately)
    // ============================
    public PropertyCardResponse(
            Long propertyId,
            String city,
            String area,
            String subArea, // ✅ NEW
            String zone, // ✅ NEW
            Integer unitTypeId,
            Double sizeSqft,
            Double price,
            Object priceUnit,
            String propertyName, // ✅ NEW
            LocalDateTime createdAt,
            String coverImageUrl) {
        this.propertyId = propertyId;
        this.city = city;
        this.area = area;
        this.subArea = subArea;
        this.zone = zone;
        this.unitTypeId = unitTypeId;
        this.sizeSqft = sizeSqft;
        this.price = price;
        this.priceUnit = (priceUnit != null) ? priceUnit.toString() : null;
        this.propertyName = propertyName;
        this.createdAt = createdAt;
        this.coverImageUrl = coverImageUrl;
    }

    // Default constructor
    public PropertyCardResponse() {
    }

    // ============================
    // Getters & Setters
    // ============================

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubArea() {
        return subArea;
    } // ✅ NEW

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public String getZone() {
        return zone;
    } // ✅ NEW

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public Double getSizeSqft() {
        return sizeSqft;
    }

    public void setSizeSqft(Double sizeSqft) {
        this.sizeSqft = sizeSqft;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPropertyName() {
        return propertyName;
    } // ✅ NEW

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<String> getTags() {
        return tags;
    } // ✅ NEW

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}