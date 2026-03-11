package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ProjectUnitType;

public interface ProjectUnitTypeRepository extends JpaRepository<ProjectUnitType, Long> {

	
	@Modifying
	@Transactional
    void deleteByProjectId(Long projectId);

    List<ProjectUnitType> findByProjectId(Long projectId);
    List<ProjectUnitType> findByProjectIdIn(List<Long> projectIds);

    // ✅ Batch query for card API
    @Query("""
    SELECT u.projectId, u.unitTypeId
    FROM ProjectUnitType u
    WHERE u.projectId IN :projectIds
    """)
    List<Object[]> findBhkTypesByProjectIds(
            @Param("projectIds") List<Long> projectIds
    );
}
