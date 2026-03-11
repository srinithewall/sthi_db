package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sthi.re.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
	List<Developer> findByDeveloperIdIn(List<Long> developerIds);
}
