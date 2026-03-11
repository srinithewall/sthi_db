package com.sthi.re.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ConstructionStatus;


public interface ConstructionStatusRepository
        extends JpaRepository<ConstructionStatus, Integer> {


	@Transactional
    Optional<ConstructionStatus> findByConstructionStatusIdAndIsActive(
            Integer constructionStatusId,
            Integer isActive
    );
	
	List<ConstructionStatus> findByIsActive(Integer isActive);
}
