package com.sthi.re.dto.response;

import java.time.LocalDateTime;

public class ProjectAmenityDto {

    private Long projectAmenityId;
    private Long projectId;
    private Integer amenityId;
    private LocalDateTime createdAt;

    public Long getProjectAmenityId() {
        return projectAmenityId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Integer getAmenityId() {
        return amenityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
