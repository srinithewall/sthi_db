package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ProjectBrochure;

public interface ProjectBrochureRepository
        extends JpaRepository<ProjectBrochure, Long> {

    List<ProjectBrochure> findByProjectIdOrderBySortOrderAsc(Long projectId);

    @Modifying
    @Transactional
	void deleteByProjectId(Long projectId);
}
