package com.sthi.re.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
	@Modifying
	@Transactional
	void deleteByProjectId(Long projectId);
	List<Location> findByProjectIdIn(List<Long> projectIds);
	
	Optional<Location> findByProjectId(Long projectId);
	// Optional<Location> findByProjectId(Long projectId);

}

