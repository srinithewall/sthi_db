package com.sthi.re.service.impl;

import com.sthi.exception.ResourceNotFoundException;
import com.sthi.model.Users;
import com.sthi.re.dto.request.AddPropertyRequest;
import com.sthi.re.dto.request.PropertyDocumentRequest;
import com.sthi.re.dto.request.PropertyImageRequest;
import com.sthi.re.dto.request.PropertySearchRequest;
import com.sthi.re.dto.request.PropertyVideoRequest;
import com.sthi.re.dto.response.OwnerDashboardResponse;
import com.sthi.re.dto.response.PropertyCardResponse;
import com.sthi.re.dto.response.PropertyCreateResponse;
import com.sthi.re.dto.response.PropertyDetailResponse;
import com.sthi.re.model.Property;
import com.sthi.re.model.PropertyAdvisor;
import com.sthi.re.model.PropertyAmenity;
import com.sthi.re.model.PropertyDocument;
import com.sthi.re.model.PropertyImage;
import com.sthi.re.model.PropertyVideo;
import com.sthi.re.model.PropertyTag;
import com.sthi.re.model.Tag;
import com.sthi.re.model.Project;
import com.sthi.re.repo.AmenityRepository;
import com.sthi.re.repo.PropertyRepository;
import com.sthi.re.repo.PropertyVideoRepository;
import com.sthi.re.repo.UserRepository;
import com.sthi.re.repo.PropertyImageRepository;
import com.sthi.re.repo.ProjectRepository;
import com.sthi.re.repo.PropertyAdvisorRepository;
import com.sthi.re.repo.PropertyAmenityRepository;
import com.sthi.re.repo.PropertyCardRepository;
import com.sthi.re.repo.PropertyDocumentRepository;
import com.sthi.re.repo.DeveloperRepository;
import com.sthi.re.repo.PropertyTagRepository;
import com.sthi.re.repo.TagRepository;
import com.sthi.re.service.PropertyService;
import com.sthi.re.service.R2StorageService;
import com.sthi.re.enums.ListedBy;
import com.sthi.re.enums.PriceUnit;
import com.sthi.re.enums.PriceUnit;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final R2StorageService r2StorageService;
    private final PropertyDocumentRepository propertyDocumentRepository;
    private final PropertyVideoRepository propertyVideoRepository;
    private final UserRepository userRepository;
    private final PropertyAdvisorRepository propertyAdvisorRepository;
    private final PropertyCardRepository propertyCardRepository;
    private final PropertyTagRepository propertyTagRepository;
    private final TagRepository tagRepository;
    private final PropertyAmenityRepository propertyAmenityRepository;
    private final AmenityRepository amenityRepository; // ✅ FIX #2: was missing

    public PropertyServiceImpl(
            PropertyRepository propertyRepository,
            PropertyImageRepository propertyImageRepository,
            PropertyDocumentRepository propertyDocumentRepository,
            PropertyVideoRepository propertyVideoRepository,
            ProjectRepository projectRepository,
            DeveloperRepository developerRepository,
            UserRepository userRepository,
            R2StorageService r2StorageService,
            PropertyAdvisorRepository propertyAdvisorRepository,
            PropertyCardRepository propertyCardRepository,
            PropertyTagRepository propertyTagRepository,
            TagRepository tagRepository,
            PropertyAmenityRepository propertyAmenityRepository,
            AmenityRepository amenityRepository // ✅ FIX #2: injected
    ) {
        this.propertyRepository = propertyRepository;
        this.propertyImageRepository = propertyImageRepository;
        this.propertyDocumentRepository = propertyDocumentRepository;
        this.propertyVideoRepository = propertyVideoRepository;
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
        this.userRepository = userRepository;
        this.r2StorageService = r2StorageService;
        this.propertyAdvisorRepository = propertyAdvisorRepository;
        this.propertyCardRepository = propertyCardRepository;
        this.propertyTagRepository = propertyTagRepository;
        this.tagRepository = tagRepository;
        this.propertyAmenityRepository = propertyAmenityRepository;
        this.amenityRepository = amenityRepository; // ✅ FIX #2: assigned
    }

    // =====================================================
    // ADD PROPERTY
    // =====================================================

    @Override
    @Transactional
    @CacheEvict(value = "property-cards", allEntries = true)
    public PropertyCreateResponse addProperty(
            AddPropertyRequest request,
            List<MultipartFile> images,
            List<MultipartFile> documents,
            List<MultipartFile> videos,
            Integer loggedInUserId) {
        validateBasicFields(request);

        // ==============================
        // Validate Price & Size
        // ==============================

        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }

        if (request.getSizeSqft() == null || request.getSizeSqft() <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }

        if (request.getPossessionDate() != null &&
                request.getPossessionDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Possession date cannot be in the past.");
        }

        Long developerId = request.getDeveloperId();
        Long projectId = request.getProjectId();

        if (projectId != null) {
            Project project = projectRepository
                    .findByProjectIdAndStatus(projectId, 1)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Project with ID " + projectId + " not found or is not active"));
            developerId = project.getDeveloperId();
        } else {
            validateStandaloneProperty(request);
        }

        // ==============================
        // 1️⃣ Build Property
        // ==============================

        Property property = new Property();

        property.setProjectId(projectId);
        property.setDeveloperId(developerId);

        property.setOwnerName(request.getOwnerName());
        property.setOwnerPhone(request.getOwnerPhone());
        property.setSellerUserId(request.getSellerUserId());

        property.setUnitTypeId(request.getUnitTypeId());
        property.setSizeSqft(request.getSizeSqft());
        property.setPrice(request.getPrice());
        property.setPriceUnit(request.getPriceUnit() != null ? PriceUnit.valueOf(request.getPriceUnit()) : null);
        property.setFloorNumber(request.getFloorNumber());
        property.setTowerName(request.getTowerName());
        property.setFacing(request.getFacing());
        property.setFurnishingStatus(request.getFurnishingStatus());
        property.setZone(request.getZone());
        property.setArea(request.getArea());
        property.setSubArea(request.getSubArea());
        property.setCity(request.getCity());
        property.setLatitude(request.getLatitude());
        property.setLongitude(request.getLongitude());

        property.setAvailabilityStatus(
                request.getAvailabilityStatus() != null
                        ? request.getAvailabilityStatus()
                        : "Available");

        property.setPropertyName(request.getPropertyName());
        property.setDescription(request.getDescription());
        property.setPossessionDate(request.getPossessionDate());

        // ✅ FIX #1: set amenitiesDescription BEFORE save so it persists
        property.setAmenitiesDescription(request.getAmenitiesDescription());

        property.setIsActive(
                request.getIsActive() != null ? request.getIsActive() : true);

        // Always false during creation
        property.setIsVerified(false);

        property.setListedBy(
                request.getListedBy() != null
                        ? request.getListedBy()
                        : ListedBy.OWNER);

        // Audit
        property.setCreatedBy(loggedInUserId);
        property.setCreatedAt(LocalDateTime.now());

        // ✅ Single save — all fields including amenitiesDescription are set above
        Property saved = propertyRepository.save(property);

        // ==============================
        // 2️⃣ Advisor Mapping
        // ==============================

        if (request.getAdvisorUserId() != null) {
            Users advisor = userRepository
                    .findByIdAndUserTypeIdAndIsActive(
                            request.getAdvisorUserId(),
                            2L,
                            1)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid advisor"));

            PropertyAdvisor mapping = new PropertyAdvisor();
            mapping.setPropertyId(saved.getPropertyId());
            mapping.setAdvisorUserId(advisor.getId());
            mapping.setStatus(1);
            mapping.setCreatedAt(LocalDateTime.now());

            propertyAdvisorRepository.save(mapping);
        }

        // ==============================
        // 3️⃣ Media
        // ==============================

        savePropertyImages(saved.getPropertyId(), request.getImages());
        savePropertyDocuments(saved.getPropertyId(), request.getDocuments());
        savePropertyVideos(saved.getPropertyId(), request.getVideos());

        // ==============================
        // 4️⃣ Tags
        // ==============================

        // ✅ FIX #3: single call — savePropertyTags handles delete internally
        savePropertyTags(saved.getPropertyId(), request.getTagIds());

        // ==============================
        // 5️⃣ Amenities (selected IDs)
        // ==============================

        savePropertyAmenities(saved.getPropertyId(), request.getAmenityIds());

        return new PropertyCreateResponse(
                saved.getPropertyId(),
                "Property created successfully");
    }

    // =====================================================
    // UPDATE PROPERTY
    // =====================================================

    @Override
    @Transactional
    @CacheEvict(value = "property-cards", allEntries = true)
    public PropertyCreateResponse updateProperty(
            Long propertyId,
            AddPropertyRequest request,
            List<MultipartFile> images,
            List<MultipartFile> documents,
            List<MultipartFile> videos,
            Integer loggedInUserId) {
        Property property = propertyRepository
                .findByPropertyIdAndIsActiveTrue(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        validateBasicFields(request);

        property.setAdvisorUserId(request.getAdvisorUserId());
        property.setOwnerName(request.getOwnerName());
        property.setOwnerPhone(request.getOwnerPhone());
        property.setUnitTypeId(request.getUnitTypeId());
        property.setSizeSqft(request.getSizeSqft());
        property.setPrice(request.getPrice());
        property.setPriceUnit(request.getPriceUnit() != null ? PriceUnit.valueOf(request.getPriceUnit()) : null);
        property.setFloorNumber(request.getFloorNumber());
        property.setTowerName(request.getTowerName());
        property.setFacing(request.getFacing());
        property.setFurnishingStatus(request.getFurnishingStatus());
        property.setAvailabilityStatus(request.getAvailabilityStatus()); // ✅ removed duplicate setFurnishingStatus

        if (request.getPropertyName() != null) {
            property.setPropertyName(request.getPropertyName());
        }

        // ✅ FIX #4: set amenitiesDescription BEFORE save so it persists
        if (request.getAmenitiesDescription() != null) {
            property.setAmenitiesDescription(request.getAmenitiesDescription());
        }

        property.setUpdatedBy(loggedInUserId);
        property.setUpdatedAt(LocalDateTime.now());

        // ✅ Single save — all fields are set above
        propertyRepository.save(property);

        // ==============================
        // Selective media handling
        // ==============================

        updatePropertyImages(propertyId, request.getImages());
        updatePropertyDocuments(propertyId, request.getDocuments());
        updatePropertyVideos(propertyId, request.getVideos());

        if (images != null && !images.isEmpty()) {
            saveNewImages(propertyId, images);
        }
        if (documents != null && !documents.isEmpty()) {
            saveNewDocuments(propertyId, documents);
        }
        if (videos != null && !videos.isEmpty()) {
            saveNewVideos(propertyId, videos);
        }

        // ==============================
        // Tags
        // ==============================

        // ✅ FIX #3: removed manual deleteByPropertyId + duplicate call.
        // savePropertyTags handles its own delete-then-insert internally.
        savePropertyTags(propertyId, request.getTagIds());

        // ==============================
        // Amenities (selected IDs)
        // ==============================

        savePropertyAmenities(propertyId, request.getAmenityIds());

        return new PropertyCreateResponse(
                propertyId,
                "Property updated successfully");
    }

    // =====================================================
    // GET PROPERTY BY ID
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "property-cards")
    public PropertyDetailResponse getPropertyById(Long propertyId) {

        Property property = propertyRepository
                .findByPropertyIdAndIsActiveTrue(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return mapToDetail(property);
    }

    // =====================================================
    // LIST PROPERTY CARDS
    // =====================================================

    @Override
    @Cacheable(value = "property-cards")
    @Transactional(readOnly = true)
    public List<PropertyCardResponse> listPropertyCards() {

        List<PropertyCardResponse> cards = propertyCardRepository.findPropertyCards();

        if (cards.isEmpty()) {
            return cards;
        }

        for (PropertyCardResponse card : cards) {
            String rawPath = card.getCoverImageUrl();
            if (rawPath != null && !rawPath.isBlank()) {
                card.setCoverImageUrl(r2StorageService.buildPublicUrl(rawPath));
            }
        }

        return cards;
    }

    // =====================================================
    // SEARCH PROPERTIES
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public Page<PropertyCardResponse> searchProperties(PropertySearchRequest request) {

        int page = request.getPage() != null ? request.getPage() : 0;
        int size = request.getSize() != null ? request.getSize() : 10;

        String sortBy = request.getSortBy() != null ? request.getSortBy() : "createdAt";
        String sortOrder = request.getSortOrder() != null ? request.getSortOrder() : "desc";

        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<PropertyCardResponse> result = propertyCardRepository.searchPropertyCards(
                request.getCity(),
                request.getArea(),
                request.getMinBudget(),
                request.getMaxBudget(),
                request.getDeveloperIds(),
                request.getProjectIds(),
                request.getUnitTypeIds(),
                pageable);

        result.getContent().forEach(card -> {
            if (card.getCoverImageUrl() != null && !card.getCoverImageUrl().isBlank()) {
                card.setCoverImageUrl(r2StorageService.buildPublicUrl(card.getCoverImageUrl()));
            }
        });

        return result;
    }

    // =====================================================
    // SOFT DELETE
    // =====================================================

    @Override
    @Transactional
    @CacheEvict(value = "property-cards", allEntries = true)
    public void deleteProperty(Long propertyId, Integer loggedInUserId) {

        Property property = propertyRepository
                .findByPropertyIdAndIsActiveTrue(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        property.setIsActive(false);
        property.setUpdatedBy(loggedInUserId);
        property.setUpdatedAt(LocalDateTime.now());

        propertyRepository.save(property);
    }

    // =====================================================
    // DELETE PROPERTY IMAGE
    // =====================================================

    @Override
    @Transactional
    public void deletePropertyImage(Long imageId) {

        PropertyImage image = propertyImageRepository
                .findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        r2StorageService.deleteFile(image.getImageUrl());

        image.setStatus(0);
        propertyImageRepository.save(image);
    }

    // =====================================================
    // OWNER DASHBOARD
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public OwnerDashboardResponse getOwnerDashboard(Integer sellerUserId) {

        OwnerDashboardResponse response = new OwnerDashboardResponse();

        Long total = propertyRepository
                .countBySellerUserIdAndIsActiveTrue(sellerUserId);

        Long active = propertyRepository
                .countBySellerUserIdAndIsActiveTrueAndAvailabilityStatus(
                        sellerUserId, "Available");

        Long inactive = total - active;

        response.setTotalProperties(total);
        response.setActiveProperties(active);
        response.setInactiveProperties(inactive);
        response.setTotalViews(0L);
        response.setTotalLeads(0L);

        return response;
    }

    // =====================================================
    // PRIVATE HELPERS — VALIDATION
    // =====================================================

    private void validateBasicFields(AddPropertyRequest request) {
        if (request.getUnitTypeId() == null)
            throw new IllegalArgumentException("Unit type required");
        if (request.getPrice() == null)
            throw new IllegalArgumentException("Price required");
        if (request.getSizeSqft() == null)
            throw new IllegalArgumentException("Size required");
    }

    private void validateStandaloneProperty(AddPropertyRequest request) {
        if (request.getZone() == null || request.getZone().isBlank())
            throw new IllegalArgumentException("Zone required");
        if (request.getArea() == null || request.getArea().isBlank())
            throw new IllegalArgumentException("Area required");
        if (request.getCity() == null || request.getCity().isBlank())
            throw new IllegalArgumentException("City required");
        if (request.getOwnerName() == null || request.getOwnerName().isBlank())
            throw new IllegalArgumentException("Owner name required");
    }

    // =====================================================
    // PRIVATE HELPERS — TAGS
    // ✅ FIX #5: removed @Transactional — has no effect on private methods
    // =====================================================

    private void savePropertyTags(Long propertyId, List<Long> tagIds) {
        // Delete-then-insert (replace strategy)
        propertyTagRepository.deleteByPropertyId(propertyId);

        if (tagIds == null || tagIds.isEmpty()) {
            return;
        }

        List<PropertyTag> propertyTags = tagIds.stream()
                .distinct()
                .map(tagId -> {
                    PropertyTag pt = new PropertyTag();
                    pt.setPropertyId(propertyId);
                    pt.setTagId(tagId);
                    return pt;
                })
                .toList();

        propertyTagRepository.saveAll(propertyTags);
    }

    // =====================================================
    // PRIVATE HELPERS — AMENITIES
    // ✅ FIX #5: removed @Transactional — has no effect on private methods
    // =====================================================

    private void savePropertyAmenities(Long propertyId, List<Long> amenityIds) {
        // Delete-then-insert (replace strategy)
        propertyAmenityRepository.deleteByPropertyId(propertyId);

        if (amenityIds == null || amenityIds.isEmpty()) {
            return;
        }

        List<PropertyAmenity> amenities = amenityIds.stream()
                .distinct()
                .map(amenityId -> {
                    PropertyAmenity pa = new PropertyAmenity();
                    pa.setPropertyId(propertyId);
                    pa.setAmenityId(amenityId);
                    return pa;
                })
                .toList();

        propertyAmenityRepository.saveAll(amenities);
    }

    // =====================================================
    // PRIVATE HELPERS — IMAGES
    // =====================================================

    private void savePropertyImages(Long propertyId, List<PropertyImageRequest> images) {

        if (images == null || images.isEmpty())
            return;

        for (PropertyImageRequest img : images) {

            if (img.getFile() == null || img.getFile().isEmpty())
                continue;

            String key = r2StorageService.uploadProjectImage(img.getFile(), propertyId);

            PropertyImage propertyImage = new PropertyImage();
            propertyImage.setPropertyId(propertyId);
            propertyImage.setImageUrl(key);
            propertyImage.setSortOrder(img.getSortOrder());
            propertyImage.setStatus(1);
            propertyImage.setCreatedAt(LocalDateTime.now());

            propertyImageRepository.save(propertyImage);
        }
    }

    private void updatePropertyImages(Long propertyId, List<PropertyImageRequest> images) {

        if (images == null || images.isEmpty())
            return;

        for (PropertyImageRequest img : images) {

            // REMOVE existing image
            if (Boolean.TRUE.equals(img.getRemove()) && img.getPropertyImageId() != null) {

                PropertyImage existing = propertyImageRepository
                        .findById(img.getPropertyImageId())
                        .orElseThrow(() -> new IllegalArgumentException("Image not found"));

                r2StorageService.deleteFile(existing.getImageUrl());
                existing.setStatus(0);
                propertyImageRepository.save(existing);
                continue;
            }

            // ADD new image
            if (img.getFile() != null && !img.getFile().isEmpty()) {

                String key = r2StorageService.uploadPropertyImage(img.getFile(), propertyId);

                PropertyImage newImage = new PropertyImage();
                newImage.setPropertyId(propertyId);
                newImage.setImageUrl(key);
                newImage.setSortOrder(img.getSortOrder());
                newImage.setStatus(1);
                newImage.setCreatedAt(LocalDateTime.now());

                propertyImageRepository.save(newImage);
            }
        }
    }

    private void saveNewImages(Long propertyId, List<MultipartFile> images) {
        for (MultipartFile file : images) {
            if (file == null || file.isEmpty())
                continue;

            String key = r2StorageService.uploadPropertyImage(file, propertyId);

            PropertyImage pi = new PropertyImage();
            pi.setPropertyId(propertyId);
            pi.setImageUrl(key);
            pi.setStatus(1);
            pi.setCreatedAt(LocalDateTime.now());

            propertyImageRepository.save(pi);
        }
    }

    // =====================================================
    // PRIVATE HELPERS — DOCUMENTS
    // =====================================================

    private void savePropertyDocuments(Long propertyId, List<PropertyDocumentRequest> documents) {

        if (documents == null || documents.isEmpty())
            return;

        for (PropertyDocumentRequest doc : documents) {

            if (doc.getFile() == null || doc.getFile().isEmpty())
                continue;

            String key = r2StorageService.uploadPropertyDocument(
                    doc.getFile(), propertyId, doc.getDocumentType());

            PropertyDocument propertyDocument = new PropertyDocument();
            propertyDocument.setPropertyId(propertyId);
            propertyDocument.setDocumentType(doc.getDocumentType());
            propertyDocument.setDocumentUrl(key);
            propertyDocument.setSortOrder(doc.getSortOrder());
            propertyDocument.setStatus(1);
            propertyDocument.setCreatedAt(LocalDateTime.now());

            propertyDocumentRepository.save(propertyDocument);
        }
    }

    private void updatePropertyDocuments(Long propertyId, List<PropertyDocumentRequest> documents) {

        if (documents == null || documents.isEmpty())
            return;

        for (PropertyDocumentRequest doc : documents) {

            // REMOVE existing document
            if (Boolean.TRUE.equals(doc.getRemove()) && doc.getPropertyDocumentId() != null) {

                PropertyDocument existing = propertyDocumentRepository
                        .findById(doc.getPropertyDocumentId())
                        .orElseThrow(() -> new IllegalArgumentException("Document not found"));

                r2StorageService.deleteFile(existing.getDocumentUrl());
                existing.setStatus(0);
                propertyDocumentRepository.save(existing);
                continue;
            }

            // ADD new document
            if (doc.getFile() != null && !doc.getFile().isEmpty()) {

                String key = r2StorageService.uploadPropertyDocument(
                        doc.getFile(), propertyId, doc.getDocumentType());

                PropertyDocument newDoc = new PropertyDocument();
                newDoc.setPropertyId(propertyId);
                newDoc.setDocumentType(doc.getDocumentType());
                newDoc.setDocumentUrl(key);
                newDoc.setSortOrder(doc.getSortOrder());
                newDoc.setStatus(1);
                newDoc.setCreatedAt(LocalDateTime.now());

                propertyDocumentRepository.save(newDoc);
            }
        }
    }

    private void saveNewDocuments(Long propertyId, List<MultipartFile> documents) {
        for (MultipartFile file : documents) {
            if (file == null || file.isEmpty())
                continue;

            String key = r2StorageService.uploadPropertyDocument(file, propertyId, "Other");

            PropertyDocument pd = new PropertyDocument();
            pd.setPropertyId(propertyId);
            pd.setDocumentUrl(key);
            pd.setDocumentType("Other");
            pd.setStatus(1);
            pd.setCreatedAt(LocalDateTime.now());

            propertyDocumentRepository.save(pd);
        }
    }

    // =====================================================
    // PRIVATE HELPERS — VIDEOS
    // =====================================================

    private void savePropertyVideos(Long propertyId, List<PropertyVideoRequest> videos) {

        if (videos == null || videos.isEmpty())
            return;

        for (PropertyVideoRequest video : videos) {

            if (video.getVideoUrl() == null || video.getVideoUrl().isBlank())
                continue;

            PropertyVideo propertyVideo = new PropertyVideo();
            propertyVideo.setPropertyId(propertyId);
            propertyVideo.setVideoUrl(video.getVideoUrl());
            propertyVideo.setVideoType(video.getVideoType());
            propertyVideo.setSortOrder(video.getSortOrder());
            propertyVideo.setStatus(1);
            propertyVideo.setCreatedAt(LocalDateTime.now());

            propertyVideoRepository.save(propertyVideo);
        }
    }

    private void updatePropertyVideos(Long propertyId, List<PropertyVideoRequest> videos) {

        if (videos == null || videos.isEmpty())
            return;

        for (PropertyVideoRequest video : videos) {

            // REMOVE existing video
            if (Boolean.TRUE.equals(video.getRemove()) && video.getPropertyVideoId() != null) {

                PropertyVideo existing = propertyVideoRepository
                        .findById(video.getPropertyVideoId())
                        .orElseThrow(() -> new IllegalArgumentException("Video not found"));

                // Soft delete only — URL may be YouTube, nothing to delete from R2
                existing.setStatus(0);
                propertyVideoRepository.save(existing);
                continue;
            }

            // ADD new video
            if (video.getVideoUrl() != null && !video.getVideoUrl().isBlank()) {

                PropertyVideo newVideo = new PropertyVideo();
                newVideo.setPropertyId(propertyId);
                newVideo.setVideoUrl(video.getVideoUrl());
                newVideo.setVideoType(video.getVideoType());
                newVideo.setSortOrder(video.getSortOrder());
                newVideo.setStatus(1);
                newVideo.setCreatedAt(LocalDateTime.now());

                propertyVideoRepository.save(newVideo);
            }
        }
    }

    private void saveNewVideos(Long propertyId, List<MultipartFile> videos) {
        // Videos are typically external URLs (YouTube/Vimeo).
        // File-based video upload can be wired here if R2 support is added.
        for (MultipartFile file : videos) {
            if (file == null || file.isEmpty())
                continue;
            // TODO: implement r2StorageService.uploadPropertyVideo(file, propertyId) when
            // ready
        }
    }

    // =====================================================
    // PRIVATE HELPERS — MAP TO DETAIL RESPONSE
    // =====================================================

    private PropertyDetailResponse mapToDetail(Property property) {

        PropertyDetailResponse response = new PropertyDetailResponse();

        response.setPropertyId(property.getPropertyId());
        response.setProjectId(property.getProjectId());
        response.setDeveloperId(property.getDeveloperId());
        response.setOwnerName(property.getOwnerName());
        response.setOwnerPhone(property.getOwnerPhone());
        response.setUnitTypeId(property.getUnitTypeId());
        response.setSizeSqft(property.getSizeSqft());
        response.setPrice(property.getPrice());
        response.setPriceUnit(property.getPriceUnit() != null ? property.getPriceUnit().name() : null);
        response.setZone(property.getZone());
        response.setArea(property.getArea());
        response.setCity(property.getCity());
        response.setPropertyName(property.getPropertyName());
        response.setCreatedAt(property.getCreatedAt());

        // Images — active only, converted to public URLs
        List<String> imageUrls = new java.util.ArrayList<>();
        for (PropertyImage img : propertyImageRepository.findByPropertyId(property.getPropertyId())) {
            if (Integer.valueOf(1).equals(img.getStatus())) {
                imageUrls.add(r2StorageService.buildPublicUrl(img.getImageUrl()));
            }
        }
        response.setImageUrls(imageUrls);

        // Tags — resolve names from tag IDs
        List<String> tags = new java.util.ArrayList<>();
        for (PropertyTag pt : propertyTagRepository.findByPropertyId(property.getPropertyId())) {
            tagRepository.findById(pt.getTagId()).ifPresent(tag -> tags.add(tag.getTagName()));
        }
        response.setTags(tags);

        // Amenities description (free-text)
        response.setAmenitiesDescription(property.getAmenitiesDescription());

        // Amenities — resolve names from selected amenity IDs
        List<String> amenityNames = new java.util.ArrayList<>();
        for (PropertyAmenity pa : propertyAmenityRepository.findByPropertyId(property.getPropertyId())) {
            amenityRepository.findById(pa.getAmenityId().intValue()).ifPresent(a -> amenityNames.add(a.getName()));
        }
        response.setAmenities(amenityNames);

        return response;
    }
}