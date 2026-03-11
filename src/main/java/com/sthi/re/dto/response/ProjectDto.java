package com.sthi.re.dto.response;

import java.time.LocalDateTime;

public class ProjectDto {

    private Long projectId;
    private String projectName;
    private String reraNumber;
    private String description;
    private String status;
    private Long developerId;
    private Integer projectTypeId;
    private Integer createdBy;
    private Integer updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getReraNumber() {
        return reraNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
