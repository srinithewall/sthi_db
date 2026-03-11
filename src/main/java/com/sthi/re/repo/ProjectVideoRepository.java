package com.sthi.re.repo;

import com.sthi.re.model.ProjectVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectVideoRepository extends JpaRepository<ProjectVideo, Long> {

    List<ProjectVideo> findByProjectIdAndStatusOrderBySortOrderAsc(
            Long projectId,
            Integer status);

    List<ProjectVideo> findByProjectId(Long projectId);
}
