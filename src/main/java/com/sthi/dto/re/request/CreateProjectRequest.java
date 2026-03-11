package com.sthi.dto.re.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class CreateProjectRequest {
    
    @NotBlank(message = "Project name is required")
    @Size(max = 255, message = "Project name cannot exceed 255 characters")
    @JsonProperty("project_name")
    private String projectName;
    
    @NotBlank(message = "Developer name is required")
    @Size(max = 255, message = "Developer name cannot exceed 255 characters")
    @JsonProperty("developer_name")
    private String developerName;
    
    @NotBlank(message = "Property type is required")
    @Size(max = 100, message = "Property type cannot exceed 100 characters")
    @JsonProperty("property_type")
    private String propertyType;
    
    @NotBlank(message = "Location area is required")
    @Size(max = 255, message = "Location area cannot exceed 255 characters")
    @JsonProperty("location_area")
    private String locationArea;
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "New Arrival|Trending|Ready to Move|Under Construction", 
             message = "Status must be one of: New Arrival, Trending, Ready to Move, Under Construction")
    private String status;
    
    @NotNull(message = "Minimum price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum price must be greater than 0")
    @JsonProperty("price_min")
    private BigDecimal priceMin;
    
    @NotNull(message = "Maximum price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Maximum price must be greater than 0")
    @JsonProperty("price_max")
    private BigDecimal priceMax;
    
    @Size(max = 5000, message = "Description cannot exceed 5000 characters")
    private String description;
    
    @Size(max = 255, message = "Cashback offer cannot exceed 255 characters")
    @JsonProperty("cashback_offer")
    private String cashbackOffer;
    
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private BigDecimal latitude;
    
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private BigDecimal longitude;
    
    @NotEmpty(message = "At least one image is required")
    @Valid
    private List<ImageRequest> images;
    
    @NotEmpty(message = "At least one unit type is required")
    @Valid
    @JsonProperty("unit_types")
    private List<UnitTypeRequest> unitTypes;
    
    private List<String> amenities;
    
    @Size(max = 100, message = "RERA number cannot exceed 100 characters")
    @JsonProperty("rera_number")
    private String reraNumber;
    
    // Optional fields with defaults
    private String city = "Bengaluru";
    private String zone = "Central";
    private String state = "Karnataka";
    private String country = "India";
    
    // Getters and Setters (MANUAL - No Lombok)
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    
    public String getDeveloperName() { return developerName; }
    public void setDeveloperName(String developerName) { this.developerName = developerName; }
    
    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }
    
    public String getLocationArea() { return locationArea; }
    public void setLocationArea(String locationArea) { this.locationArea = locationArea; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public BigDecimal getPriceMin() { return priceMin; }
    public void setPriceMin(BigDecimal priceMin) { this.priceMin = priceMin; }
    
    public BigDecimal getPriceMax() { return priceMax; }
    public void setPriceMax(BigDecimal priceMax) { this.priceMax = priceMax; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCashbackOffer() { return cashbackOffer; }
    public void setCashbackOffer(String cashbackOffer) { this.cashbackOffer = cashbackOffer; }
    
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    
    public List<ImageRequest> getImages() { return images; }
    public void setImages(List<ImageRequest> images) { this.images = images; }
    
    public List<UnitTypeRequest> getUnitTypes() { return unitTypes; }
    public void setUnitTypes(List<UnitTypeRequest> unitTypes) { this.unitTypes = unitTypes; }
    
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
    
    public String getReraNumber() { return reraNumber; }
    public void setReraNumber(String reraNumber) { this.reraNumber = reraNumber; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}