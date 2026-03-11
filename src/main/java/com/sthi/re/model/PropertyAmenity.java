package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "re_property_amenities",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"property_id", "amenity_id"})
    }
)
public class PropertyAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_amenity_id")
    private Long propertyAmenityId;

    @Column(name = "property_id", nullable = false)
    private Long propertyId;

    @Column(name = "amenity_id", nullable = false)
    private Long amenityId;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getPropertyAmenityId() { return propertyAmenityId; }
    public void setPropertyAmenityId(Long propertyAmenityId) { this.propertyAmenityId = propertyAmenityId; }

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public Long getAmenityId() { return amenityId; }
    public void setAmenityId(Long amenityId) { this.amenityId = amenityId; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}