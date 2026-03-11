package com.sthi.dto.re.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponse {
    
    @JsonProperty("project_id")
    private Long projectId;
    
    @JsonProperty("project_name")
    private String projectName;
    
    @JsonProperty("developer_info")
    private DeveloperResponse developer;
    
    @JsonProperty("property_type")
    private String propertyType;
    
    @JsonProperty("location_info")
    private LocationResponse location;
    
    private String status;
    
    @JsonProperty("price_min")
    private BigDecimal priceMin;
    
    @JsonProperty("price_max")
    private BigDecimal priceMax;
    
    private String description;
    
    @JsonProperty("cashback_offer")
    private String cashbackOffer;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;
    
    @JsonProperty("rera_number")
    private String reraNumber;
    
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonProperty("images")
    private List<ImageResponse> images = new ArrayList<>();
    
    @JsonProperty("unit_types")
    private List<UnitTypeResponse> unitTypes = new ArrayList<>();
    
    @JsonProperty("amenities")
    private List<String> amenities = new ArrayList<>();
    
    // Constructors
    public ProjectResponse() {
    }
    
    public ProjectResponse(Long projectId, String projectName, DeveloperResponse developer,
                         String propertyType, LocationResponse location, String status,
                         BigDecimal priceMin, BigDecimal priceMax, String description,
                         String cashbackOffer, BigDecimal latitude, BigDecimal longitude,
                         String reraNumber, LocalDateTime createdAt, List<ImageResponse> images,
                         List<UnitTypeResponse> unitTypes, List<String> amenities) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.developer = developer;
        this.propertyType = propertyType;
        this.location = location;
        this.status = status;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.description = description;
        this.cashbackOffer = cashbackOffer;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reraNumber = reraNumber;
        this.createdAt = createdAt;
        this.images = images != null ? images : new ArrayList<>();
        this.unitTypes = unitTypes != null ? unitTypes : new ArrayList<>();
        this.amenities = amenities != null ? amenities : new ArrayList<>();
    }
    
    // Builder method
    public static ProjectResponseBuilder builder() {
        return new ProjectResponseBuilder();
    }
    
    // Getters and Setters
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
    
    public DeveloperResponse getDeveloper() {
        return developer;
    }
    
    public void setDeveloper(DeveloperResponse developer) {
        this.developer = developer;
    }
    
    public String getPropertyType() {
        return propertyType;
    }
    
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    
    public LocationResponse getLocation() {
        return location;
    }
    
    public void setLocation(LocationResponse location) {
        this.location = location;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public BigDecimal getPriceMin() {
        return priceMin;
    }
    
    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }
    
    public BigDecimal getPriceMax() {
        return priceMax;
    }
    
    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCashbackOffer() {
        return cashbackOffer;
    }
    
    public void setCashbackOffer(String cashbackOffer) {
        this.cashbackOffer = cashbackOffer;
    }
    
    public BigDecimal getLatitude() {
        return latitude;
    }
    
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    public BigDecimal getLongitude() {
        return longitude;
    }
    
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    public String getReraNumber() {
        return reraNumber;
    }
    
    public void setReraNumber(String reraNumber) {
        this.reraNumber = reraNumber;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<ImageResponse> getImages() {
        return images;
    }
    
    public void setImages(List<ImageResponse> images) {
        this.images = images != null ? images : new ArrayList<>();
    }
    
    public List<UnitTypeResponse> getUnitTypes() {
        return unitTypes;
    }
    
    public void setUnitTypes(List<UnitTypeResponse> unitTypes) {
        this.unitTypes = unitTypes != null ? unitTypes : new ArrayList<>();
    }
    
    public List<String> getAmenities() {
        return amenities;
    }
    
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities != null ? amenities : new ArrayList<>();
    }
    
    // Builder class
    public static class ProjectResponseBuilder {
        private Long projectId;
        private String projectName;
        private DeveloperResponse developer;
        private String propertyType;
        private LocationResponse location;
        private String status;
        private BigDecimal priceMin;
        private BigDecimal priceMax;
        private String description;
        private String cashbackOffer;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private String reraNumber;
        private LocalDateTime createdAt;
        private List<ImageResponse> images = new ArrayList<>();
        private List<UnitTypeResponse> unitTypes = new ArrayList<>();
        private List<String> amenities = new ArrayList<>();
        
        public ProjectResponseBuilder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }
        
        public ProjectResponseBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }
        
        public ProjectResponseBuilder developer(DeveloperResponse developer) {
            this.developer = developer;
            return this;
        }
        
        public ProjectResponseBuilder propertyType(String propertyType) {
            this.propertyType = propertyType;
            return this;
        }
        
        public ProjectResponseBuilder location(LocationResponse location) {
            this.location = location;
            return this;
        }
        
        public ProjectResponseBuilder status(String status) {
            this.status = status;
            return this;
        }
        
        public ProjectResponseBuilder priceMin(BigDecimal priceMin) {
            this.priceMin = priceMin;
            return this;
        }
        
        public ProjectResponseBuilder priceMax(BigDecimal priceMax) {
            this.priceMax = priceMax;
            return this;
        }
        
        public ProjectResponseBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public ProjectResponseBuilder cashbackOffer(String cashbackOffer) {
            this.cashbackOffer = cashbackOffer;
            return this;
        }
        
        public ProjectResponseBuilder latitude(BigDecimal latitude) {
            this.latitude = latitude;
            return this;
        }
        
        public ProjectResponseBuilder longitude(BigDecimal longitude) {
            this.longitude = longitude;
            return this;
        }
        
        public ProjectResponseBuilder reraNumber(String reraNumber) {
            this.reraNumber = reraNumber;
            return this;
        }
        
        public ProjectResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        
        public ProjectResponseBuilder images(List<ImageResponse> images) {
            this.images = images != null ? images : new ArrayList<>();
            return this;
        }
        
        public ProjectResponseBuilder unitTypes(List<UnitTypeResponse> unitTypes) {
            this.unitTypes = unitTypes != null ? unitTypes : new ArrayList<>();
            return this;
        }
        
        public ProjectResponseBuilder amenities(List<String> amenities) {
            this.amenities = amenities != null ? amenities : new ArrayList<>();
            return this;
        }
        
        public ProjectResponse build() {
            return new ProjectResponse(projectId, projectName, developer, propertyType,
                                     location, status, priceMin, priceMax, description,
                                     cashbackOffer, latitude, longitude, reraNumber,
                                     createdAt, images, unitTypes, amenities);
        }
    }
}