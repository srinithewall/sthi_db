package com.sthi.re.repo;

import com.sthi.re.model.ProjectAdvisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectAdvisorRepository extends JpaRepository<ProjectAdvisor, Long> {

    Optional<ProjectAdvisor> findByProjectIdAndStatus(Long projectId, Integer status);

    boolean existsByProjectIdAndStatus(Long projectId, Integer status);

    Optional<ProjectAdvisor> findByProjectId(Long projectId);
    void deleteByProjectId(Long projectId); 
}
