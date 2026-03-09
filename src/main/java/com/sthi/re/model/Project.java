package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "rera_number")
    private String reraNumber;

    @Column(name = "description", length = 1000)
    private String description;

    /**
     * 1 = Active
     * 0 = Deleted (soft delete)
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "developer_id")
    private Long developerId;

    @Column(name = "project_type_id")
    private Integer projectTypeId;

    @Column(name = "possession_date")
    private LocalDate possessionDate;

    @Column(name = "rera_status")
    private Integer reraStatus;

    @Column(name = "construction_status_id")
    private Integer constructionStatusid;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "source_type")
    private String sourceType;

    @Column(name = "source_name")
    private String sourceName;

    /**
     * 1 = Verified
     * 0 = Not Verified
     */
    @Column(name = "is_verified")
    private Integer isVerified;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    public String getReraNumber() {
        return reraNumber;
    }

    public void setReraNumber(String reraNumber) {
        this.reraNumber = reraNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public LocalDate getPossessionDate() {
        return possessionDate;
    }

    public void setPossessionDate(LocalDate possessionDate) {
        this.possessionDate = possessionDate;
    }

    public Integer getReraStatus() {
        return reraStatus;
    }

    public void setReraStatus(Integer reraStatus) {
        this.reraStatus = reraStatus;
    }

    public Integer getConstructionStatusid() {
        return constructionStatusid;
    }

    public void setConstructionStatusid(Integer constructionStatusid) {
        this.constructionStatusid = constructionStatusid;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
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
}
