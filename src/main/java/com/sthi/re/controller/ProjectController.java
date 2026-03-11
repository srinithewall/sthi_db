package com.sthi.re.controller;

import com.sthi.re.dto.request.AddProjectRequest;
import com.sthi.re.dto.request.ProjectSearchRequest;
import com.sthi.re.dto.request.UpdateProjectRequest;
import com.sthi.re.dto.response.ProjectCreateResponse;
import com.sthi.re.dto.response.ProjectDetailResponse;
import com.sthi.re.service.ProjectService;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sthi.re.dto.response.ApiResponse;
import com.sthi.re.dto.response.ProjectCardResponse;

@RestController
@RequestMapping("/api/re/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Add Project API
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectCreateResponse> addProject(
            @ModelAttribute AddProjectRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId) {

        // Temporary fallback until security is wired
        if (loggedInUserId == null) {
            loggedInUserId = 1; // SYSTEM / ADMIN user
        }

        ProjectCreateResponse response = projectService.addProject(request, loggedInUserId);

        return ResponseEntity.ok(response);
    }

    /**
     * Update Project API
     */
    @PutMapping(value = "/{projectId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectCreateResponse> updateProject(
            @PathVariable Long projectId,
            @ModelAttribute UpdateProjectRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId) {

        // Temporary fallback until security is wired
        if (loggedInUserId == null) {
            loggedInUserId = 1; // SYSTEM / ADMIN user
        }

        ProjectCreateResponse response = projectService.updateProject(projectId, request, loggedInUserId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader(value = "X-USER-ID", required = false) Integer userId) {

        if (userId == null) {
            userId = 1;
        }

        boolean deleted = projectService.deleteProject(projectId, userId);

        if (deleted) {
            return ResponseEntity.ok(
                    new ApiResponse(true, "Project soft deleted successfully"));
        } else {
            return ResponseEntity.ok(
                    new ApiResponse(true, "Project already deleted"));
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponse> getProjectById(
            @PathVariable Long projectId) {

        ProjectDetailResponse response = projectService.getProjectById(projectId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<ProjectCardResponse>> listProjectCards(
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId) {

        return ResponseEntity.ok(
                projectService.listProjectCards(loggedInUserId));
    }

    /**
     * List Staged (Unverified) Projects
     */
    @GetMapping("/unverified")
    public ResponseEntity<List<ProjectCardResponse>> listUnverifiedProjectCards() {

        return ResponseEntity.ok(
                projectService.listUnverifiedProjectCards());
    }

    /**
     * Verify and Publish Project
     */
    @PatchMapping("/{projectId}/verify")
    public ResponseEntity<ProjectCreateResponse> verifyProject(
            @PathVariable Long projectId,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId) {

        if (loggedInUserId == null) {
            loggedInUserId = 1;
        }

        ProjectCreateResponse response = projectService.verifyProject(projectId, loggedInUserId);

        return ResponseEntity.ok(response);
    }

    /**
     * Project Search API (Parameter-based)
     */
    @PostMapping("/search")
    public ResponseEntity<List<ProjectCardResponse>> searchProjects(
            @RequestBody ProjectSearchRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId) {

        return ResponseEntity.ok(
                projectService.searchProjects(request, loggedInUserId));
    }

}
