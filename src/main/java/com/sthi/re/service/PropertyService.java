package com.sthi.re.service;

import com.sthi.re.dto.request.AddPropertyRequest;
import com.sthi.re.dto.request.PropertySearchRequest;
import com.sthi.re.dto.response.OwnerDashboardResponse;
import com.sthi.re.dto.response.PropertyCardResponse;
import com.sthi.re.dto.response.PropertyCreateResponse;
import com.sthi.re.dto.response.PropertyDetailResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface PropertyService {

    // =============================
    // CREATE PROPERTY
    // =============================
    PropertyCreateResponse addProperty(
            AddPropertyRequest request,
            List<MultipartFile> images,
            List<MultipartFile> documents,
            List<MultipartFile> videos,
            Integer loggedInUserId
    );

    // Update Property
	PropertyCreateResponse updateProperty(
	        Long propertyId,
	        AddPropertyRequest request,
	        List<MultipartFile> images,
	        List<MultipartFile> documents,
	        List<MultipartFile> videos,
	        Integer loggedInUserId
	);

    // Get Property By Id
    PropertyDetailResponse getPropertyById(Long propertyId);

    // Soft Delete
    void deleteProperty(Long propertyId, Integer loggedInUserId);
    


	void deletePropertyImage(Long imageId);
	
	Page<PropertyCardResponse> searchProperties(PropertySearchRequest request);

	OwnerDashboardResponse getOwnerDashboard(Integer sellerUserId);
	
	List<PropertyCardResponse> listPropertyCards();


}
