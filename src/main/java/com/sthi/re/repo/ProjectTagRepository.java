package com.sthi.re.repo;

import com.sthi.re.model.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {

    List<ProjectTag> findByProjectIdAndStatus(Long projectId, Integer status);

    boolean existsByProjectIdAndTagIdAndStatus(
            Long projectId,
            Long tagId,
            Integer status
    );
    void deleteByProjectId(Long projectId);
}
