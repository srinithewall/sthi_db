package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_lead_routing_config")
public class ReLeadRoutingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * NULL = applies to all projects (global routing)
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * FIXED_ADVISOR
     * ROUND_ROBIN
     * LEAST_LOAD
     */
    @Column(name = "routing_type")
    private String routingType;

    /**
     * FK to users.id
     * Used only when routing_type = FIXED_ADVISOR
     */
    @Column(name = "fixed_advisor_id")
    private Integer fixedAdvisorId;

    /**
     * 1 = Active
     * 0 = Inactive
     */
    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRoutingType() {
        return routingType;
    }

    public void setRoutingType(String routingType) {
        this.routingType = routingType;
    }

    public Integer getFixedAdvisorId() {
        return fixedAdvisorId;
    }

    public void setFixedAdvisorId(Integer fixedAdvisorId) {
        this.fixedAdvisorId = fixedAdvisorId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

    // ---------- Helper Methods (Optional but Useful) ----------

    @Transient
    public boolean isActiveConfig() {
        return isActive != null && isActive == 1;
    }

    @Transient
    public boolean isFixedAdvisorRouting() {
        return "FIXED_ADVISOR".equalsIgnoreCase(routingType);
    }
}
