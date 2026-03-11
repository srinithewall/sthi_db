package com.sthi.re.repo;

import com.sthi.re.model.PropertyDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyDocumentRepository
        extends JpaRepository<PropertyDocument, Long> {

    List<PropertyDocument> findByPropertyIdAndStatus(Long propertyId, Integer status);

    void deleteByPropertyId(Long propertyId);
}