package com.sthi.re.dto.request;

import java.time.LocalDate;
import java.util.List;

public class ProjectSearchRequest {
	
	private String sortBy = "recent";   // recent | price
	private String sortOrder = "desc";  // asc | desc
	private Integer page = 0;
	private Integer size = 20;

    /* ---------------- Trust & Compliance ---------------- */

    // RERA registered projects only
    private Boolean reraRegistered;

    /* ---------------- Project Status ---------------- */

    // Multiple construction statuses allowed
    private List<Integer> constructionStatusIds;

    // Projects with possession date on or before this date
    private LocalDate possessionBefore;

    /* ---------------- Location (Radius Based) ---------------- */

    // Selected location latitude
    private Double latitude;

    // Selected location longitude
    private Double longitude;

    // Search radius in kilometers (default = 10km)
    private Integer radiusKm = 10;

    /* ---------------- Configuration ---------------- */

    // BHK / Unit type IDs (2BHK, 3BHK, Plot, etc.)
    private List<Integer> unitTypeIds;

    // Amenity IDs (Gym, Pool, etc.)
    private List<Integer> amenityIds;

    /* ---------------- Budget ---------------- */

    // Minimum budget
    private Double minBudget;

    // Maximum budget
    private Double maxBudget;

    /* ---------------- Discovery ---------------- */

    // Multiple developers allowed
    private List<Integer> developerIds;

    // Multiple project types allowed
    private List<Integer> projectTypeIds;

    /* ---------------- Getters & Setters ---------------- */

    public Boolean getReraRegistered() {
        return reraRegistered;
    }

    public void setReraRegistered(Boolean reraRegistered) {
        this.reraRegistered = reraRegistered;
    }

    public List<Integer> getConstructionStatusIds() {
        return constructionStatusIds;
    }

    public void setConstructionStatusIds(List<Integer> constructionStatusIds) {
        this.constructionStatusIds = constructionStatusIds;
    }

    public LocalDate getPossessionBefore() {
        return possessionBefore;
    }

    public void setPossessionBefore(LocalDate possessionBefore) {
        this.possessionBefore = possessionBefore;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getRadiusKm() {
        return radiusKm;
    }

    public void setRadiusKm(Integer radiusKm) {
        this.radiusKm = radiusKm;
    }

    public List<Integer> getUnitTypeIds() {
        return unitTypeIds;
    }

    public void setUnitTypeIds(List<Integer> unitTypeIds) {
        this.unitTypeIds = unitTypeIds;
    }

    public List<Integer> getAmenityIds() {
        return amenityIds;
    }

    public void setAmenityIds(List<Integer> amenityIds) {
        this.amenityIds = amenityIds;
    }

    public Double getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(Double minBudget) {
        this.minBudget = minBudget;
    }

    public Double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public List<Integer> getDeveloperIds() {
        return developerIds;
    }

    public void setDeveloperIds(List<Integer> developerIds) {
        this.developerIds = developerIds;
    }

    public List<Integer> getProjectTypeIds() {
        return projectTypeIds;
    }

    public void setProjectTypeIds(List<Integer> projectTypeIds) {
        this.projectTypeIds = projectTypeIds;
    }
    
	public String getSortBy() {
		return sortBy;
	}
    
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
