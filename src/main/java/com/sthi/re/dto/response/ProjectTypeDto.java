package com.sthi.re.dto.response;

import java.time.LocalDateTime;

public class ProjectTypeDto {

    private Integer projectTypeId;
    private String name;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
