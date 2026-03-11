package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ProjectImage;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
	
	@Modifying
	@Transactional
	void deleteByProjectId(Long projectId);
	
	List<ProjectImage> findByProjectIdOrderBySortOrderAsc(Long projectId);
	
	ProjectImage findFirstByProjectIdOrderBySortOrderAsc(Long projectId);
	
	@Query("""
			SELECT pi FROM ProjectImage pi
			WHERE pi.projectId IN :projectIds
			AND pi.status = 1
			AND pi.sortOrder = (
			   SELECT MIN(p2.sortOrder)
			   FROM ProjectImage p2
			   WHERE p2.projectId = pi.projectId
			   AND p2.status = 1
			)
			""")
			List<ProjectImage> findCoverImagesByProjectIds(@Param("projectIds") List<Long> projectIds);


}
