package com.sthi.re.repo;

import com.sthi.re.model.PropertyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PropertyTagRepository extends JpaRepository<PropertyTag, Long> {

    List<PropertyTag> findByPropertyId(Long propertyId);

    

    	@Transactional
        @Modifying
        @Query("DELETE FROM PropertyTag pt WHERE pt.propertyId = :propertyId")
        void deleteByPropertyId(@Param("propertyId") Long propertyId);

}