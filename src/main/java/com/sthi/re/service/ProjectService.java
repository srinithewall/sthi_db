package com.sthi.re.service;

import java.util.List;

import com.sthi.re.dto.request.AddProjectRequest;
import com.sthi.re.dto.request.ProjectSearchRequest;
import com.sthi.re.dto.request.UpdateProjectRequest;
import com.sthi.re.dto.response.ProjectCardResponse;
import com.sthi.re.dto.response.ProjectCreateResponse;
import com.sthi.re.dto.response.ProjectDetailResponse;
import com.sthi.re.dto.response.ProjectListResponse;

public interface ProjectService {

        ProjectCreateResponse addProject(
                        AddProjectRequest request,
                        Integer loggedInUserId);

        ProjectCreateResponse updateProject(
                        Long projectId,
                        UpdateProjectRequest request,
                        Integer loggedInUserId);

        // 🔹 NEW: Get project by ID
        ProjectDetailResponse getProjectById(Long projectId);

        // 🔹 NEW: Soft delete
        boolean deleteProject(Long projectId, Integer loggedInUserId);

        // List<ProjectListResponse> listProjects();

        List<ProjectCardResponse> listProjectCards(Integer loggedInUserId);

        List<ProjectCardResponse> listUnverifiedProjectCards();

        ProjectCreateResponse verifyProject(Long projectId, Integer adminUserId);

        List<ProjectCardResponse> searchProjects(ProjectSearchRequest request, Integer loggedInUserId);

}
