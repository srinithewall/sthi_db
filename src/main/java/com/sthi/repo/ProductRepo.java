package com.sthi.repo;

import com.sthi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // Find products by category ID
    List<Product> findByCategoryId(Long categoryId);
    
    // Find products by vendor ID
    List<Product> findByVendorId(Long vendorId);
    
    // Custom query for filtering products
    @Query("SELECT p FROM Product p WHERE " +
           "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
           "(:vendorId IS NULL OR p.vendor.id = :vendorId) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findWithFilters(@Param("categoryId") Long categoryId,
                                  @Param("vendorId") Long vendorId,
                                  @Param("minPrice") Double minPrice,
                                  @Param("maxPrice") Double maxPrice);
    
    // Check if product exists by name (optional)
    boolean existsByName(String name);
    
    // Find active products
    List<Product> findByActiveTrue();
    
    // Find products by name containing (search)
    List<Product> findByNameContainingIgnoreCase(String name);
}