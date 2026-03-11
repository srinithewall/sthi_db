package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ProjectAmenity;

public interface ProjectAmenityRepository
extends JpaRepository<ProjectAmenity, Long> {

@Modifying
@Transactional
@Query("delete from ProjectAmenity pa where pa.projectId = :projectId")
void deleteByProjectId(@Param("projectId") Long projectId);

List<ProjectAmenity> findByProjectId(Long projectId);
}

