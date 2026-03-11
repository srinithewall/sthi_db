package com.sthi.re.repo;

import com.sthi.re.model.PropertyAdvisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyAdvisorRepository
        extends JpaRepository<PropertyAdvisor, Long> {

    Optional<PropertyAdvisor> findByPropertyIdAndStatus(Long propertyId, Integer status);

    void deleteByPropertyId(Long propertyId);
}