package com.sthi.re.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sthi.re.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
}
