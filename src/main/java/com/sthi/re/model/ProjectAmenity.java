package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_project_amenities")
public class ProjectAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_amenity_id")
    private Long projectAmenityId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "amenity_id")
    private Integer amenityId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getProjectAmenityId() {
        return projectAmenityId;
    }

    public void setProjectAmenityId(Long projectAmenityId) {
        this.projectAmenityId = projectAmenityId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(Integer amenityId) {
        this.amenityId = amenityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
