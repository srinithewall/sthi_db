package com.sthi.re.dto.response;

import java.time.LocalDateTime;

public class LocationDto {

    private Long locationId;
    private Long projectId;
    private Double latitude;
    private Double longitude;
    private String city;
    private String area;
    private String zone;
    private String addressLine;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters (already present)

    public Long getLocationId() {
        return locationId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getZone() {
        return zone;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // ✅ ADD THESE SETTERS

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
