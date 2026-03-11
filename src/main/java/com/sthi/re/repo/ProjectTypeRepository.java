package com.sthi.re.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ProjectType;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Integer> {
	

}
