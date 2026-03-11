package com.sthi.re.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sthi.re.enums.ListedBy;

import java.time.LocalDate;
import java.util.List;

public class AddPropertyRequest {

    // =========================
    // OPTIONAL RELATIONS
    // =========================
    private Long developerId;
    private Long projectId;
    private Integer advisorUserId;
    private Integer sellerUserId;

    // =========================
    // OWNER INFO (Standalone)
    // =========================
    private String ownerName;
    private String ownerPhone;

    // =========================
    // PROPERTY DETAILS
    // =========================
    private String propertyName;
    private Integer unitTypeId;
    private Double sizeSqft;
    private Double price;
    private String priceUnit;

    private Integer floorNumber;
    private String towerName;
    private String facing;
    private String furnishingStatus;
    private String availabilityStatus;

    // =========================
    // LOCATION
    // =========================
    private String zone;
    private String area;
    
    private String subArea;
    
    private String city;
    private Double latitude;
    private Double longitude;

    // =========================
    // TAGS
    // =========================
    private List<Long> tagIds;

    // =========================
    // NEW LISTING FIELDS
    // =========================
    private Boolean isActive;
    private ListedBy listedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate possessionDate;

    private String description;

    // =========================
    // MEDIA
    // =========================
    private List<PropertyImageRequest> images;
    private List<PropertyDocumentRequest> documents;
    private List<PropertyVideoRequest> videos;
    // Free-text amenities (user types their own description)
    private String amenitiesDescription;

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================
    


    // Selected amenity IDs from lookup
    private List<Long> amenityIds;

    public String getAmenitiesDescription() { return amenitiesDescription; }
    public void setAmenitiesDescription(String amenitiesDescription) { this.amenitiesDescription = amenitiesDescription; }

    public List<Long> getAmenityIds() { return amenityIds; }
    public void setAmenityIds(List<Long> amenityIds) { this.amenityIds = amenityIds; }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public Long getDeveloperId() { return developerId; }
    public void setDeveloperId(Long developerId) { this.developerId = developerId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Integer getAdvisorUserId() { return advisorUserId; }
    public void setAdvisorUserId(Integer advisorUserId) { this.advisorUserId = advisorUserId; }

    public Integer getSellerUserId() { return sellerUserId; }
    public void setSellerUserId(Integer sellerUserId) { this.sellerUserId = sellerUserId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerPhone() { return ownerPhone; }
    public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public Integer getUnitTypeId() { return unitTypeId; }
    public void setUnitTypeId(Integer unitTypeId) { this.unitTypeId = unitTypeId; }

    public Double getSizeSqft() { return sizeSqft; }
    public void setSizeSqft(Double sizeSqft) { this.sizeSqft = sizeSqft; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getPriceUnit() { return priceUnit; }
    public void setPriceUnit(String priceUnit) { this.priceUnit = priceUnit; }

    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }

    public String getTowerName() { return towerName; }
    public void setTowerName(String towerName) { this.towerName = towerName; }

    public String getFacing() { return facing; }
    public void setFacing(String facing) { this.facing = facing; }

    public String getFurnishingStatus() { return furnishingStatus; }
    public void setFurnishingStatus(String furnishingStatus) { this.furnishingStatus = furnishingStatus; }

    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public List<Long> getTagIds() { return tagIds; }
    public void setTagIds(List<Long> tagIds) { this.tagIds = tagIds; }

    // 🔥 IMPORTANT: Boolean getter should be getIsActive()
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public ListedBy getListedBy() { return listedBy; }
    public void setListedBy(ListedBy listedBy) { this.listedBy = listedBy; }

    public LocalDate getPossessionDate() { return possessionDate; }
    public void setPossessionDate(LocalDate possessionDate) { this.possessionDate = possessionDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<PropertyImageRequest> getImages() { return images; }
    public void setImages(List<PropertyImageRequest> images) { this.images = images; }

    public List<PropertyDocumentRequest> getDocuments() { return documents; }
    public void setDocuments(List<PropertyDocumentRequest> documents) { this.documents = documents; }

    public List<PropertyVideoRequest> getVideos() { return videos; }
    public void setVideos(List<PropertyVideoRequest> videos) { this.videos = videos; }
}