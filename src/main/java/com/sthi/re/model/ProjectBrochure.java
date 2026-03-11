package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_project_brochures")
public class ProjectBrochure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brochure_id")
    private Long brochureId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "brochure_url", nullable = false)
    private String brochureUrl;

    @Column(name = "brochure_type")
    private String brochureType;   // PDF, FLOOR_PLAN, MASTER_PLAN

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // -------- Getters & Setters --------

    public Long getBrochureId() {
        return brochureId;
    }

    public void setBrochureId(Long brochureId) {
        this.brochureId = brochureId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getBrochureUrl() {
        return brochureUrl;
    }

    public void setBrochureUrl(String brochureUrl) {
        this.brochureUrl = brochureUrl;
    }

    public String getBrochureType() {
        return brochureType;
    }

    public void setBrochureType(String brochureType) {
        this.brochureType = brochureType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
