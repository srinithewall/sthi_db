package com.sthi.re.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDetailResponse {

    private Long projectId;
    private String projectName;
    private String reraNumber;
    private String description;
    private String status;
    private String sourceType;
    private String sourceName;
    private LocalDateTime sourceUpdatedAt;

    private Long developerId;
    private Integer projectTypeId;

    private LocationDto location;

    private List<ProjectUnitTypeDto> unitTypes;
    private List<Integer> amenityIds;
    private List<ProjectImageDto> images;

    private String constructionStatus;
    private List<ProjectBrochureDto> brochures;

    // ✅ NEW
    private List<ProjectDocumentDto> documents;
    private List<ProjectVideoDto> videos;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ============================
    // Getters & Setters
    // ============================

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getReraNumber() { return reraNumber; }
    public void setReraNumber(String reraNumber) { this.reraNumber = reraNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }

    public String getSourceName() { return sourceName; }
    public void setSourceName(String sourceName) { this.sourceName = sourceName; }

    public LocalDateTime getSourceUpdatedAt() { return sourceUpdatedAt; }
    public void setSourceUpdatedAt(LocalDateTime sourceUpdatedAt) { this.sourceUpdatedAt = sourceUpdatedAt; }

    public Long getDeveloperId() { return developerId; }
    public void setDeveloperId(Long developerId) { this.developerId = developerId; }

    public Integer getProjectTypeId() { return projectTypeId; }
    public void setProjectTypeId(Integer projectTypeId) { this.projectTypeId = projectTypeId; }

    public LocationDto getLocation() { return location; }
    public void setLocation(LocationDto location) { this.location = location; }

    public List<ProjectUnitTypeDto> getUnitTypes() { return unitTypes; }
    public void setUnitTypes(List<ProjectUnitTypeDto> unitTypes) { this.unitTypes = unitTypes; }

    public List<Integer> getAmenityIds() { return amenityIds; }
    public void setAmenityIds(List<Integer> amenityIds) { this.amenityIds = amenityIds; }

    public List<ProjectImageDto> getImages() { return images; }
    public void setImages(List<ProjectImageDto> images) { this.images = images; }

    public String getConstructionStatus() { return constructionStatus; }
    public void setConstructionStatus(String constructionStatus) { this.constructionStatus = constructionStatus; }

    public List<ProjectBrochureDto> getBrochures() { return brochures; }
    public void setBrochures(List<ProjectBrochureDto> brochures) { this.brochures = brochures; }

    public List<ProjectDocumentDto> getDocuments() { return documents; }
    public void setDocuments(List<ProjectDocumentDto> documents) { this.documents = documents; }

    public List<ProjectVideoDto> getVideos() { return videos; }
    public void setVideos(List<ProjectVideoDto> videos) { this.videos = videos; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
