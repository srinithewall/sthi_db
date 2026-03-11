package com.sthi.re.dto.request;

import java.util.List;

public class PropertySearchRequest {

    // 🔹 Filters
    private List<Long> developerIds;
    private List<Long> projectIds;
    private List<Integer> unitTypeIds;

    private Double minBudget;
    private Double maxBudget;

    private String city;
    private String area;

    // 🔹 Sorting
    private String sortBy;      // price / recent
    private String sortOrder;   // asc / desc

    // 🔹 Pagination
    private Integer page;       // default 0
    private Integer size;       // default 10

    // =========================
    // Getters & Setters
    // =========================

    public List<Long> getDeveloperIds() {
        return developerIds;
    }

    public void setDeveloperIds(List<Long> developerIds) {
        this.developerIds = developerIds;
    }

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public List<Integer> getUnitTypeIds() {
        return unitTypeIds;
    }

    public void setUnitTypeIds(List<Integer> unitTypeIds) {
        this.unitTypeIds = unitTypeIds;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
