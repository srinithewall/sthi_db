package com.sthi.re.repo;

import com.sthi.re.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // ACTIVE PROPERTIES
    List<Property> findByIsActiveTrue();

    Optional<Property> findByPropertyIdAndIsActiveTrue(Long propertyId);

    List<Property> findByProjectIdAndIsActiveTrue(Long projectId);

    List<Property> findByDeveloperIdAndIsActiveTrue(Long developerId);

    List<Property> findByIsActiveTrueAndIsVerifiedTrue();

    // OWNER DASHBOARD
    Long countBySellerUserIdAndIsActiveTrue(Integer sellerUserId);

    Long countBySellerUserIdAndIsActiveTrueAndAvailabilityStatus(
            Integer sellerUserId,
            String availabilityStatus
    );
}