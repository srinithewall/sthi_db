package com.sthi.re.dto.response;

public class ProjectCreateResponse {

    private Long projectId;
    private String message;

    public ProjectCreateResponse(Long projectId, String message) {
        this.projectId = projectId;
        this.message = message;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getMessage() {
        return message;
    }
}
