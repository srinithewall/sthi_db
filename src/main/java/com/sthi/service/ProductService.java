package com.sthi.service;

import com.sthi.model.Product;
import com.sthi.dto.ProductDTO;
import com.sthi.dto.CreateProductRequest;
import com.sthi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @Value("${app.base-url:http://localhost:8081}")
    private String baseUrl;

    // Convert Entity to DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setNameKn(product.getNameKn()); // This will include the Kannada name from DB
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        
        // CHANGE: Convert filename to full URL for frontend
        if (product.getImageUrl() != null) {
            dto.setImageUrl(baseUrl + "/uploads/products/" + product.getImageUrl());
        } else {
            dto.setImageUrl(null);
        }
        
        dto.setActive(product.getActive());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        
        if (product.getVendor() != null) {
            dto.setVendorId(product.getVendor().getId());
            dto.setVendorName(product.getVendor().getName());
        }
        
        return dto;
    }
    
    // Convert CreateProductRequest to Entity
    private Product convertToEntity(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setNameKn(request.getNameKn());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        // Note: imageUrl will be set separately after file storage
        product.setActive(request.getActive());
        
        return product;
    }

    // NEW: Create product with image file
    public ProductDTO createProductWithImage(CreateProductRequest request, MultipartFile imageFile) {
        Product product = convertToEntity(request);
        
        // Save product first to get ID
        Product savedProduct = productRepo.save(product);
        
        // Save image after product has ID
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = fileStorageService.storeFile(imageFile, savedProduct.getId());
            savedProduct.setImageUrl(fileName); // Store only filename
            savedProduct = productRepo.save(savedProduct);
        }
        
        return convertToDTO(savedProduct);
    }

    // NEW: Update product with image file
    public Optional<ProductDTO> updateProductWithImage(Long id, CreateProductRequest request, MultipartFile imageFile) {
        return productRepo.findById(id).map(existingProduct -> {
            existingProduct.setName(request.getName());
            existingProduct.setNameKn(request.getNameKn());
            existingProduct.setDescription(request.getDescription());
            existingProduct.setPrice(request.getPrice());
            existingProduct.setStockQuantity(request.getStockQuantity());
            existingProduct.setActive(request.getActive());
            
            // Handle image update if new file is provided
            if (imageFile != null && !imageFile.isEmpty()) {
                // Delete old image file
                if (existingProduct.getImageUrl() != null) {
                    fileStorageService.deleteFile(existingProduct.getImageUrl());
                }
                // Store new image file
                String fileName = fileStorageService.storeFile(imageFile, id);
                existingProduct.setImageUrl(fileName);
            }
            
            Product updatedProduct = productRepo.save(existingProduct);
            return convertToDTO(updatedProduct);
        });
    }

    // Keep existing methods for backward compatibility
    public ProductDTO createProduct(CreateProductRequest request) {
        Product product = convertToEntity(request);
        Product savedProduct = productRepo.save(product);
        return convertToDTO(savedProduct);
    }

    public Optional<ProductDTO> updateProduct(Long id, CreateProductRequest request) {
        return productRepo.findById(id).map(existingProduct -> {
            existingProduct.setName(request.getName());
            existingProduct.setNameKn(request.getNameKn());
            existingProduct.setDescription(request.getDescription());
            existingProduct.setPrice(request.getPrice());
            existingProduct.setStockQuantity(request.getStockQuantity());
            existingProduct.setImageUrl(request.getImageUrl()); // Keep existing behavior
            existingProduct.setActive(request.getActive());
            
            Product updatedProduct = productRepo.save(existingProduct);
            return convertToDTO(updatedProduct);
        });
    }

    // Update delete to also remove image file
    public boolean deleteProduct(Long id) {
        Optional<Product> productOpt = productRepo.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            // Delete associated image file
            if (product.getImageUrl() != null) {
                fileStorageService.deleteFile(product.getImageUrl());
            }
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // All read methods remain the same
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepo.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByVendor(Long vendorId) {
        return productRepo.findByVendorId(vendorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsWithFilters(Long categoryId, Long vendorId, 
                                                  Double minPrice, Double maxPrice) {
        List<Product> products = productRepo.findAll();
        
        return products.stream()
                .filter(product -> categoryId == null || 
                    (product.getCategory() != null && product.getCategory().getId().equals(categoryId)))
                .filter(product -> vendorId == null || 
                    (product.getVendor() != null && product.getVendor().getId().equals(vendorId)))
                .filter(product -> minPrice == null || 
                    (product.getPrice() != null && 
                     product.getPrice().compareTo(BigDecimal.valueOf(minPrice)) >= 0))
                .filter(product -> maxPrice == null || 
                    (product.getPrice() != null && 
                     product.getPrice().compareTo(BigDecimal.valueOf(maxPrice)) <= 0))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public boolean productExists(Long id) {
        return productRepo.existsById(id);
    }
    
    @Transactional(readOnly = true)
    public List<ProductDTO> searchProducts(String name) {
        return productRepo.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ProductDTO> getActiveProducts() {
        return productRepo.findByActiveTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}