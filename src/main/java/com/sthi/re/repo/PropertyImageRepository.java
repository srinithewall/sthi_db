package com.sthi.re.repo;

import com.sthi.re.model.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {

    List<PropertyImage> findByPropertyId(Long propertyId);

    PropertyImage findFirstByPropertyIdOrderBySortOrderAsc(Long propertyId);
    
    PropertyImage findFirstByPropertyIdAndStatusOrderBySortOrderAsc(
            Long propertyId,
            Integer status
    );

}
