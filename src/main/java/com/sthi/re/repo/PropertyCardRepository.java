package com.sthi.re.repo;

import com.sthi.re.dto.response.PropertyCardResponse;
import com.sthi.re.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyCardRepository extends JpaRepository<Property, Long> {

    // =====================================================
    // PROPERTY CARDS (HOME LIST)
    // =====================================================
    @Query("""
        SELECT new com.sthi.re.dto.response.PropertyCardResponse(
            p.propertyId,
            p.city,
            p.area,
            p.subArea,
            p.zone,
            p.unitTypeId,
            p.sizeSqft,
            p.price,
            p.priceUnit,
            p.propertyName,
            p.createdAt,
            MIN(pi.imageUrl)
        )
        FROM Property p
        LEFT JOIN PropertyImage pi
            ON pi.propertyId = p.propertyId
            AND pi.status = 1
        WHERE p.isActive = true
        AND p.isVerified = true
        GROUP BY
            p.propertyId,
            p.city,
            p.area,
            p.subArea,
            p.zone,
            p.unitTypeId,
            p.sizeSqft,
            p.price,
            p.priceUnit,
            p.propertyName,
            p.createdAt
        ORDER BY p.createdAt DESC
    """)
    List<PropertyCardResponse> findPropertyCards();


    // =====================================================
    // SEARCH
    // =====================================================
    @Query("""
        SELECT new com.sthi.re.dto.response.PropertyCardResponse(
            p.propertyId,
            p.city,
            p.area,
            p.subArea,
            p.zone,
            p.unitTypeId,
            p.sizeSqft,
            p.price,
            p.priceUnit,
            p.propertyName,
            p.createdAt,
            MIN(pi.imageUrl)
        )
        FROM Property p
        LEFT JOIN PropertyImage pi
            ON pi.propertyId = p.propertyId
            AND pi.status = 1
        WHERE p.isActive = true
        AND p.isVerified = true
        AND (:city IS NULL OR p.city = :city)
        AND (:area IS NULL OR p.area = :area)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        AND (:developerIds IS NULL OR p.developerId IN :developerIds)
        AND (:projectIds IS NULL OR p.projectId IN :projectIds)
        AND (:unitTypeIds IS NULL OR p.unitTypeId IN :unitTypeIds)
        GROUP BY
            p.propertyId,
            p.city,
            p.area,
            p.subArea,
            p.zone,
            p.unitTypeId,
            p.sizeSqft,
            p.price,
            p.priceUnit,
            p.propertyName,
            p.createdAt
    """)
    Page<PropertyCardResponse> searchPropertyCards(
            @Param("city") String city,
            @Param("area") String area,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("developerIds") List<Long> developerIds,
            @Param("projectIds") List<Long> projectIds,
            @Param("unitTypeIds") List<Integer> unitTypeIds,
            Pageable pageable
    );

    // =====================================================
    // ✅ NEW — Fetch tag names for a given property
    // =====================================================
    @Query("""
        SELECT t.tagName
        FROM PropertyTag pt
        JOIN Tag t ON pt.tagId = t.tagId
        WHERE pt.propertyId = :propertyId
        AND pt.isActive = true
    """)
    List<String> findTagNamesByPropertyId(@Param("propertyId") Long propertyId);
}