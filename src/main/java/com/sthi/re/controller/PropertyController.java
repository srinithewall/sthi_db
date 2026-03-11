package com.sthi.re.controller;

import com.sthi.re.dto.request.AddPropertyRequest;
import com.sthi.re.dto.request.PropertyDocumentRequest;
import com.sthi.re.dto.request.PropertyImageRequest;
import com.sthi.re.dto.request.PropertySearchRequest;
import com.sthi.re.dto.request.PropertyVideoRequest;
import com.sthi.re.dto.response.OwnerDashboardResponse;
import com.sthi.re.dto.response.PropertyCardResponse;
import com.sthi.re.dto.response.PropertyCreateResponse;
import com.sthi.re.dto.response.PropertyDetailResponse;
import com.sthi.re.service.PropertyService;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/re/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    // =====================================================
    // ADD PROPERTY (Multipart Version) - Support Flat Form-Data
    // =====================================================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropertyCreateResponse> addProperty(
            @ModelAttribute AddPropertyRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId
    ) {

        if (loggedInUserId == null) {
            loggedInUserId = 1;
        }

        // Extract files from the DTO lists
        List<MultipartFile> imageFiles = request.getImages() != null 
                ? request.getImages().stream()
                        .map(PropertyImageRequest::getFile)
                        .filter(f -> f != null && !f.isEmpty())
                        .toList() 
                : null;
        
        List<MultipartFile> docFiles = request.getDocuments() != null 
                ? request.getDocuments().stream()
                        .map(PropertyDocumentRequest::getFile)
                        .filter(f -> f != null && !f.isEmpty())
                        .toList() 
                : null;
                
        List<MultipartFile> videoFiles = null; // Videos are typically URLs, check DTO if files are needed

        PropertyCreateResponse response =
                propertyService.addProperty(
                        request,
                        imageFiles,
                        docFiles,
                        videoFiles,
                        loggedInUserId
                );

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // UPDATE PROPERTY (Multipart Version) - Support Flat Form-Data
    // =====================================================
    @PutMapping(
            value = "/{propertyId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<PropertyCreateResponse> updateProperty(
            @PathVariable Long propertyId,
            @ModelAttribute AddPropertyRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId
    ) {

        if (loggedInUserId == null) {
            loggedInUserId = 1;
        }

        // Extract files from the DTO lists
        List<MultipartFile> imageFiles = request.getImages() != null 
                ? request.getImages().stream()
                        .map(PropertyImageRequest::getFile)
                        .filter(f -> f != null && !f.isEmpty())
                        .toList() 
                : null;
        
        List<MultipartFile> docFiles = request.getDocuments() != null 
                ? request.getDocuments().stream()
                        .map(PropertyDocumentRequest::getFile)
                        .filter(f -> f != null && !f.isEmpty())
                        .toList() 
                : null;

        PropertyCreateResponse response =
                propertyService.updateProperty(
                        propertyId,
                        request,
                        imageFiles,
                        docFiles,
                        null, // Videos are typically URLs
                        loggedInUserId
                );

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // GET PROPERTY BY ID
    // =====================================================
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDetailResponse> getPropertyById(
            @PathVariable Long propertyId
    ) {

        PropertyDetailResponse response =
                propertyService.getPropertyById(propertyId);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // SEARCH PROPERTIES (Marketplace)
    // =====================================================
    @PostMapping("/search")
    public ResponseEntity<Page<PropertyCardResponse>> searchProperties(
            @RequestBody PropertySearchRequest request
    ) {

        return ResponseEntity.ok(
                propertyService.searchProperties(request)
        );
    }

    // =====================================================
    // DELETE PROPERTY (Soft Delete)
    // =====================================================
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteProperty(
            @PathVariable Long propertyId,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId
    ) {

        if (loggedInUserId == null) {
            loggedInUserId = 1;
        }

        propertyService.deleteProperty(propertyId, loggedInUserId);

        return ResponseEntity.ok("Property deleted successfully");
    }

    // =====================================================
    // LIST PROPERTY CARDS
    // =====================================================
    @GetMapping("/cards")
    public ResponseEntity<List<PropertyCardResponse>> listPropertyCards() {

        return ResponseEntity.ok(
                propertyService.listPropertyCards()
        );
    }

    // =====================================================
    // DELETE PROPERTY IMAGE
    // =====================================================
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<String> deletePropertyImage(
            @PathVariable Long imageId
    ) {

        propertyService.deletePropertyImage(imageId);

        return ResponseEntity.ok("Image deleted successfully");
    }

    // =====================================================
    // OWNER DASHBOARD
    // =====================================================
    @GetMapping("/owner/dashboard")
    public ResponseEntity<OwnerDashboardResponse> getDashboard(
            @RequestHeader("X-USER-ID") Integer sellerUserId
    ) {

        return ResponseEntity.ok(
                propertyService.getOwnerDashboard(sellerUserId)
        );
    }
}