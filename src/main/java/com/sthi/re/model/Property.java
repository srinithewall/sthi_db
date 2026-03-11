package com.sthi.re.model;

import com.sthi.re.enums.ListedBy;
import com.sthi.re.enums.PriceUnit;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    // ========================
    // BASIC INFO
    // ========================

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // ========================
    // OPTIONAL RELATIONS
    // ========================

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "developer_id")
    private Long developerId;

    @Column(name = "seller_user_id")
    private Integer sellerUserId;

    // ========================
    // OWNER INFO
    // ========================

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_phone")
    private String ownerPhone;

    // ========================
    // PROPERTY DETAILS
    // ========================

    @Column(name = "unit_type_id")
    private Integer unitTypeId;

    @Column(name = "size_sqft")
    private Double sizeSqft;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_unit")
    private PriceUnit priceUnit;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "tower_name")
    private String towerName;

    @Column(name = "facing")
    private String facing;

    @Column(name = "furnishing_status")
    private String furnishingStatus;

    @Column(name = "availability_status")
    private String availabilityStatus; // Available / Sold / Blocked

    @Column(name = "advisor_user_id")
    private Integer advisorUserId;

    // Inside the PROPERTY DETAILS section
    @Column(name = "amenities_description", columnDefinition = "TEXT")
    private String amenitiesDescription;

    // ========================
    // LOCATION
    // ========================

    private String zone;

    @Column(name = "sub_area")
    private String subArea;

    private String area;
    private String city;

    private Double latitude;
    private Double longitude;

    // ========================
    // NEW LISTING FIELDS
    // ========================

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "listed_by")
    private ListedBy listedBy;

    @Column(name = "possession_date")
    private LocalDate possessionDate;

    // ========================
    // AUDIT FIELDS
    // ========================

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ========================
    // GETTERS & SETTERS
    // ========================

    // Getter
    public String getAmenitiesDescription() {
        return amenitiesDescription;
    }

    // Setter
    public void setAmenitiesDescription(String amenitiesDescription) {
        this.amenitiesDescription = amenitiesDescription;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public Integer getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(Integer sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
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

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public String getFurnishingStatus() {
        return furnishingStatus;
    }

    public void setFurnishingStatus(String furnishingStatus) {
        this.furnishingStatus = furnishingStatus;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Integer getAdvisorUserId() {
        return advisorUserId;
    }

    public void setAdvisorUserId(Integer advisorUserId) {
        this.advisorUserId = advisorUserId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public ListedBy getListedBy() {
        return listedBy;
    }

    public void setListedBy(ListedBy listedBy) {
        this.listedBy = listedBy;
    }

    public LocalDate getPossessionDate() {
        return possessionDate;
    }

    public void setPossessionDate(LocalDate possessionDate) {
        this.possessionDate = possessionDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }
}