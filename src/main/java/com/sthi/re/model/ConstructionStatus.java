package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_construction_status")
public class ConstructionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "construction_status_id")
    private Integer constructionStatusId;

    @Column(name = "code", nullable = false, unique = true)
    private String code;   // UNDER_CONSTRUCTION, READY_TO_MOVE

    @Column(name = "label", nullable = false)
    private String label;  // Under Construction, Ready to Move
    


    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // -------- Getters & Setters --------

    public Integer getConstructionStatusId() {
        return constructionStatusId;
    }

    public void setConstructionStatusId(Integer constructionStatusId) {
        this.constructionStatusId = constructionStatusId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
}
