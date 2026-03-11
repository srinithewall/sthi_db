package com.sthi.re.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sthi.re.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
    // Get single active project
    Optional<Project> findByProjectIdAndStatus(Long projectId, Integer status);

    // Get all active projects
    // Get all active projects
    List<Project> findByStatus(Integer status);

    // Get projects by status and verification status
    List<Project> findByStatusAndIsVerified(Integer status, Integer isVerified);

    boolean existsByProjectNameIgnoreCaseAndStatus(String projectName, Integer status);

    boolean existsByProjectNameIgnoreCaseAndStatusAndProjectIdNot(
            String projectName,
            Integer status,
            Long projectId);

}
