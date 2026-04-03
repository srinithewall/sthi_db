package com.sthi.re.dto.response;

import java.time.LocalDate;
import java.util.List;

public class ProjectCardResponse {

    private Long projectId;
    private String projectName;

    private String city;
    private String area;

    // Budget
    private Double minPrice;
    private Double maxPrice;
    private String priceUnit; // Cr / Lac
    private Boolean reraStatus;
    private String constructionStatus;
    private LocalDate possessionDate;
    private String developerName;
    private String projectType;

    // Units
    private List<Integer> bhkTypes; // [2,3,4]

    // Image
    private String coverImageUrl;
    
    // Website URL
    private String websiteUrl;

    // ✅ Constructor for JPQL projection
    public ProjectCardResponse(
            Long projectId,
            String projectName,
            String city,
            String area,
            Double minPrice,
            Double maxPrice,
            Object priceUnit,
            Boolean reraStatus,
            String constructionStatus,
            LocalDate possessionDate,
            String developerName,
            String projectType,
            String coverImageUrl,
            String websiteUrl) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.city = city;
        this.area = area;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.priceUnit = (priceUnit != null) ? priceUnit.toString() : null;
        this.reraStatus = reraStatus;
        this.constructionStatus = constructionStatus;
        this.possessionDate = possessionDate;
        this.developerName = developerName;
        this.projectType = projectType;
        this.coverImageUrl = coverImageUrl;
        this.websiteUrl = websiteUrl;
    }

    public ProjectCardResponse() {
    }

    // getters & setters
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public List<Integer> getBhkTypes() {
        return bhkTypes;
    }

    public void setBhkTypes(List<Integer> bhkTypes) {
        this.bhkTypes = bhkTypes;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Boolean getReraStatus() {
        return reraStatus;
    }

    public void setReraStatus(Boolean reraStatus) {
        this.reraStatus = reraStatus;
    }

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public LocalDate getPossessionDate() {
        return possessionDate;
    }

    public void setPossessionDate(LocalDate possessionDate) {
        this.possessionDate = possessionDate;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

}
