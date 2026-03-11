package com.sthi.re.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sthi.re.model.PropertyVideo;

import java.util.List;

public interface PropertyVideoRepository
        extends JpaRepository<PropertyVideo, Long> {

    List<PropertyVideo> findByPropertyIdAndStatus(Long propertyId, Integer status);

    void deleteByPropertyId(Long propertyId);
}