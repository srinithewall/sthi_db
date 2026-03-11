package com.sthi.re.service.impl;

import com.sthi.model.Users;
import com.sthi.re.dto.request.*;
import com.sthi.re.dto.response.*;
import com.sthi.re.enums.PriceUnit;
import com.sthi.re.enums.SourceType;
import com.sthi.re.model.*;
import com.sthi.re.repo.*;
import com.sthi.re.service.ProjectService;
import com.sthi.re.service.R2StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectTypeRepository projectTypeRepository;
    private final LocationRepository locationRepository;
    private final UnitTypeRepository unitTypeRepository;
    private final ProjectUnitTypeRepository projectUnitTypeRepository;
    private final AmenityRepository amenityRepository;
    private final ProjectAmenityRepository projectAmenityRepository;
    private final ProjectImageRepository projectImageRepository;
    private final ConstructionStatusRepository constructionStatusRepository;
    private final ProjectBrochureRepository projectBrochureRepository;
    private final R2StorageService r2StorageService;
    private final ProjectCardRepository projectCardRepository;
    private final UserRepository userRepository;
    private final ProjectAdvisorRepository projectAdvisorRepository;
    private final ProjectVideoRepository projectVideoRepository;
    private final ProjectDocumentRepository projectDocumentRepository;
    private final ProjectTagRepository projectTagRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
            DeveloperRepository developerRepository,
            ProjectTypeRepository projectTypeRepository,
            LocationRepository locationRepository,
            UnitTypeRepository unitTypeRepository,
            ProjectUnitTypeRepository projectUnitTypeRepository,
            AmenityRepository amenityRepository,
            ProjectAmenityRepository projectAmenityRepository,
            ConstructionStatusRepository constructionStatusRepository,
            ProjectBrochureRepository projectBrochureRepository,
            ProjectImageRepository projectImageRepository,
            R2StorageService r2StorageService,
            ProjectCardRepository projectCardRepository,
            UserRepository userRepository,
            ProjectAdvisorRepository projectAdvisorRepository,
            ProjectVideoRepository projectVideoRepository,
            ProjectDocumentRepository projectDocumentRepository,
            ProjectTagRepository projectTagRepository) {

        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
        this.projectTypeRepository = projectTypeRepository;
        this.locationRepository = locationRepository;
        this.unitTypeRepository = unitTypeRepository;
        this.projectUnitTypeRepository = projectUnitTypeRepository;
        this.amenityRepository = amenityRepository;
        this.projectAmenityRepository = projectAmenityRepository;
        this.constructionStatusRepository = constructionStatusRepository;
        this.projectBrochureRepository = projectBrochureRepository;
        this.projectImageRepository = projectImageRepository;
        this.r2StorageService = r2StorageService;
        this.projectCardRepository = projectCardRepository;
        this.userRepository = userRepository;
        this.projectAdvisorRepository = projectAdvisorRepository;
        this.projectVideoRepository = projectVideoRepository;
        this.projectDocumentRepository = projectDocumentRepository;
        this.projectTagRepository = projectTagRepository;
    }

    @Value("${cloudflare.r2.public-base-url}")
    private String r2PublicBaseUrl;
    private static final String SOURCE_TYPE_MANUAL = "MANUAL";
    private static final String SOURCE_TYPE_WEB_SCRAPING = "WEB_SCRAPING";

    private String buildPublicUrl(String path) {
        if (path == null || path.isBlank()) {
            return null;
        }
        // Backward compatibility for old data
        if (path.startsWith("http")) {
            return path;
        }
        return r2PublicBaseUrl + "/" + path;
    }

    private String normalizeSourceType(String sourceType) {
        if (sourceType == null || sourceType.isBlank()) {
            return SOURCE_TYPE_MANUAL;
        }
        return sourceType.trim().toUpperCase();
    }

    private boolean isValidSourceType(String sourceType) {
        try {
            SourceType.valueOf(sourceType);
            return true;
        } catch (Exception e) {
            return "WEB_SCRAPING".equals(sourceType);
        }
    }

    private SourceType mapSourceType(String sourceType) {
        if ("WEB_SCRAPING".equals(sourceType)) {
            return SourceType.SCRAPER;
        }
        try {
            return SourceType.valueOf(sourceType);
        } catch (Exception e) {
            return SourceType.MANUAL;
        }
    }

    private String normalizeSourceName(String sourceName) {
        if (sourceName == null) {
            return null;
        }
        String trimmed = sourceName.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private boolean isAdmin(Integer userId) {
        if (userId == null)
            return false;
        return userRepository.findById(userId)
                .map(u -> u.getUserTypeId() != null && u.getUserTypeId() == 1)
                .orElse(false);
    }

    // AddPROJECTSERVICE

    @Override
    @Transactional
    @CacheEvict(value = "project-cards", allEntries = true)
    public ProjectCreateResponse addProject(
            AddProjectRequest request,
            Integer loggedInUserId) {

        // ==============================
        // 1️⃣ BASIC VALIDATION
        // ==============================
        if (request.getProjectName() == null || request.getProjectName().isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }
        if (request.getDeveloperId() == null) {
            throw new IllegalArgumentException("Developer ID is required");
        }
        if (request.getProjectTypeId() == null) {
            throw new IllegalArgumentException("Project type ID is required");
        }
        if (request.getLocation() == null) {
            throw new IllegalArgumentException("Location is required");
        }

        String sourceType = normalizeSourceType(request.getSourceType());
        String sourceName = normalizeSourceName(request.getSourceName());
        if (!isValidSourceType(sourceType)) {
            throw new IllegalArgumentException("sourceType must be MANUAL or WEB_SCRAPING");
        }
        if (sourceName == null) {
            throw new IllegalArgumentException("sourceName is required (third-party or website name)");
        }

        developerRepository.findById(request.getDeveloperId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid developerId"));

        projectTypeRepository.findById(request.getProjectTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid projectTypeId"));

        if (projectRepository.existsByProjectNameIgnoreCaseAndStatus(
                request.getProjectName(), 1)) {
            throw new IllegalArgumentException("Project name already exists");
        }

        // ==============================
        // 2️⃣ CREATE PROJECT
        // ==============================
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setReraNumber(request.getReraNumber());

        project.setReraStatus(
                request.getReraNumber() != null && !request.getReraNumber().isBlank() ? 1 : 0);

        project.setDeveloperId(request.getDeveloperId());
        project.setProjectTypeId(request.getProjectTypeId());
        project.setConstructionStatusid(request.getConstructionStatusid());
        project.setPossessionDate(request.getPossessionDate());

        project.setWebsiteUrl(request.getWebsiteUrl());
        project.setSourceType(mapSourceType(sourceType));
        project.setSourceName(sourceName);
        project.setIsVerified(
                request.getIsVerified() != null && request.getIsVerified() ? 1 : 0);

        project.setStatus(1);
        project.setCreatedBy(loggedInUserId);
        project.setCreatedAt(LocalDateTime.now());

        Project savedProject = projectRepository.save(project);

        // ==============================
        // 3️⃣ LOCATION
        // ==============================
        LocationRequest loc = request.getLocation();

        Location location = new Location();
        location.setProjectId(savedProject.getProjectId());
        location.setZone(loc.getZone());
        location.setArea(loc.getArea());
        location.setCity(loc.getCity());
        location.setLatitude(loc.getLatitude());
        location.setLongitude(loc.getLongitude());
        location.setCreatedAt(LocalDateTime.now());

        locationRepository.save(location);

        // ==============================
        // 4️⃣ ADVISOR (1 PER PROJECT)
        // ==============================
        Integer advisorUserId = request.getAdvisorUserId();

        // If advisor not provided, pick any active advisor (user_type_id = 2)
        if (advisorUserId == null) {

            List<Users> advisors = userRepository.findByUserTypeIdAndIsActive(2L, 1);

            if (advisors == null || advisors.isEmpty()) {
                throw new IllegalStateException("No active advisor found");
            }

            advisorUserId = advisors.get(0).getId(); // default advisor
        }

        // Validate advisor is active
        userRepository.findByIdAndIsActive(advisorUserId, 1)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or inactive advisor"));

        ProjectAdvisor advisor = new ProjectAdvisor();
        advisor.setProjectId(savedProject.getProjectId());
        advisor.setAdvisorUserId(advisorUserId);
        advisor.setStatus(1);
        advisor.setCreatedAt(LocalDateTime.now());

        projectAdvisorRepository.save(advisor);

        // ==============================
        // 5️⃣ UNIT TYPES (SORTED)
        // ==============================
        if (request.getUnitTypes() != null) {
            request.getUnitTypes().stream()
                    .sorted(Comparator.comparing(ProjectUnitTypeRequest::getUnitTypeId))
                    .forEach(ut -> {
                        ProjectUnitType p = new ProjectUnitType();
                        p.setProjectId(savedProject.getProjectId());
                        p.setUnitTypeId(ut.getUnitTypeId());
                        p.setSizeSqft(ut.getSizeSqft());
                        p.setPriceMin(ut.getPriceMin());
                        p.setPriceMax(ut.getPriceMax());
                        p.setPriceUnit(ut.getPriceUnit() != null ? PriceUnit.valueOf(ut.getPriceUnit()) : null);
                        p.setCreatedAt(LocalDateTime.now());
                        projectUnitTypeRepository.save(p);
                    });
        }

        // ==============================
        // 6️⃣ AMENITIES (SORTED)
        // ==============================
        if (request.getAmenityIds() != null) {
            request.getAmenityIds().stream()
                    .sorted()
                    .forEach(amenityId -> {
                        ProjectAmenity pa = new ProjectAmenity();
                        pa.setProjectId(savedProject.getProjectId());
                        pa.setAmenityId(amenityId);
                        pa.setCreatedAt(LocalDateTime.now());
                        projectAmenityRepository.save(pa);
                    });
        }

        // ==============================
        // 7️⃣ IMAGES
        // ==============================
        if (request.getImages() != null) {
            for (ProjectImageRequest img : request.getImages()) {
                String imageUrl = r2StorageService.uploadProjectImage(
                        img.getFile(), savedProject.getProjectId());

                ProjectImage pi = new ProjectImage();
                pi.setProjectId(savedProject.getProjectId());
                pi.setImageUrl(imageUrl);
                pi.setSortOrder(img.getSortOrder());
                pi.setCreatedAt(LocalDateTime.now());

                projectImageRepository.save(pi);
            }
        }

        // ==============================
        // 8️⃣ DOCUMENTS
        // ==============================
        if (request.getDocuments() != null) {
            for (ProjectDocumentRequest doc : request.getDocuments()) {

                String docUrl = r2StorageService.uploadProjectDocument(
                        doc.getFile(),
                        savedProject.getProjectId(),
                        doc.getDocumentType());

                ProjectDocument pd = new ProjectDocument();
                pd.setProjectId(savedProject.getProjectId());
                pd.setDocumentType(doc.getDocumentType());
                pd.setDocumentUrl(docUrl);
                pd.setSortOrder(doc.getSortOrder());
                pd.setStatus(1);
                pd.setCreatedAt(LocalDateTime.now());

                projectDocumentRepository.save(pd);
            }
        }

        // ==============================
        // 9️⃣ VIDEOS
        // ==============================
        if (request.getVideos() != null) {
            for (ProjectVideoRequest v : request.getVideos()) {
                ProjectVideo pv = new ProjectVideo();
                pv.setProjectId(savedProject.getProjectId());
                pv.setVideoUrl(v.getVideoUrl());
                pv.setVideoType(v.getVideoType());
                pv.setSortOrder(v.getSortOrder());
                pv.setStatus(1);
                pv.setCreatedAt(LocalDateTime.now());

                projectVideoRepository.save(pv);
            }
        }

        // ==============================
        // 🔟 TAGS
        // ==============================
        if (request.getTagIds() != null) {
            request.getTagIds().stream()
                    .distinct()
                    .forEach(tagId -> {
                        ProjectTag pt = new ProjectTag();
                        pt.setProjectId(savedProject.getProjectId());
                        pt.setTagId(tagId);
                        pt.setStatus(1);
                        pt.setCreatedAt(LocalDateTime.now());
                        projectTagRepository.save(pt);
                    });
        }

        // ==============================
        // ✅ RESPONSE
        // ==============================
        return new ProjectCreateResponse(
                savedProject.getProjectId(),
                "Project created successfully");
    }

    @Override
    @Transactional
    @CacheEvict(value = "project-cards", allEntries = true)
    public ProjectCreateResponse updateProject(
            Long projectId,
            UpdateProjectRequest request,
            Integer loggedInUserId) {

        // ==============================
        // 1️⃣ Validate Project Exists
        // ==============================
        Project project = projectRepository
                .findByProjectIdAndStatus(projectId, 1)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        // ==============================
        // 2️⃣ Duplicate Name Check (if changed)
        // ==============================
        if (request.getProjectName() != null &&
                !request.getProjectName().equalsIgnoreCase(project.getProjectName())) {

            if (projectRepository
                    .existsByProjectNameIgnoreCaseAndStatusAndProjectIdNot(
                            request.getProjectName(),
                            1,
                            projectId)) {
                throw new IllegalArgumentException("Another project with same name exists");
            }

            project.setProjectName(request.getProjectName());
        }

        // ==============================
        // 3️⃣ Update Core Fields
        // ==============================
        if (request.getDescription() != null)
            project.setDescription(request.getDescription());

        if (request.getReraNumber() != null) {
            project.setReraNumber(request.getReraNumber());
            project.setReraStatus(
                    request.getReraNumber().isBlank() ? 0 : 1);
        }

        if (request.getWebsiteUrl() != null)
            project.setWebsiteUrl(request.getWebsiteUrl());

        String sourceType = request.getSourceType() != null
                ? normalizeSourceType(request.getSourceType())
                : (project.getSourceType() != null ? project.getSourceType().name() : null);
        String sourceName = request.getSourceName() != null
                ? normalizeSourceName(request.getSourceName())
                : project.getSourceName();

        if (sourceType != null && !sourceType.isBlank() && !isValidSourceType(sourceType)) {
            throw new IllegalArgumentException("sourceType must be MANUAL or WEB_SCRAPING");
        }
        if (request.getSourceName() != null && sourceName == null) {
            throw new IllegalArgumentException("sourceName cannot be blank");
        }

        if (request.getSourceType() != null) {
            project.setSourceType(mapSourceType(sourceType));
        }
        if (request.getSourceName() != null) {
            project.setSourceName(sourceName);
        }

        if (request.getIsVerified() != null)
            project.setIsVerified(request.getIsVerified() ? 1 : 0);

        if (request.getConstructionStatusid() != null)
            project.setConstructionStatusid(request.getConstructionStatusid());

        if (request.getPossessionDate() != null)
            project.setPossessionDate(request.getPossessionDate());

        project.setUpdatedBy(loggedInUserId);
        project.setUpdatedAt(LocalDateTime.now());

        projectRepository.save(project);

        // ==============================
        // 4️⃣ Update Location (NO DELETE)
        // ==============================
        if (request.getLocation() != null) {

            Location location = locationRepository
                    .findByProjectId(projectId)
                    .orElseThrow(() -> new IllegalArgumentException("Location not found"));

            LocationRequest loc = request.getLocation();

            location.setZone(loc.getZone());
            location.setArea(loc.getArea());
            location.setCity(loc.getCity());
            location.setLatitude(loc.getLatitude());
            location.setLongitude(loc.getLongitude());
        }

        // ==============================
        // 5️⃣ Replace Unit Types
        // ==============================
        if (request.getUnitTypes() != null) {

            projectUnitTypeRepository.deleteByProjectId(projectId);

            for (ProjectUnitTypeRequest ut : request.getUnitTypes()) {

                unitTypeRepository.findById(ut.getUnitTypeId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid unitTypeId"));

                ProjectUnitType p = new ProjectUnitType();
                p.setProjectId(projectId);
                p.setUnitTypeId(ut.getUnitTypeId());
                p.setSizeSqft(ut.getSizeSqft());
                p.setPriceMin(ut.getPriceMin());
                p.setPriceMax(ut.getPriceMax());
                p.setPriceUnit(ut.getPriceUnit() != null ? PriceUnit.valueOf(ut.getPriceUnit()) : null);
                p.setCreatedAt(LocalDateTime.now());

                projectUnitTypeRepository.save(p);
            }
        }

        // ==============================
        // 6️⃣ Replace Amenities
        // ==============================
        if (request.getAmenityIds() != null) {

            projectAmenityRepository.deleteByProjectId(projectId);

            for (Integer amenityId : request.getAmenityIds()) {

                amenityRepository.findById(amenityId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid amenityId"));

                ProjectAmenity pa = new ProjectAmenity();
                pa.setProjectId(projectId);
                pa.setAmenityId(amenityId);
                pa.setCreatedAt(LocalDateTime.now());

                projectAmenityRepository.save(pa);
            }
        }

        // ==============================
        // 7️⃣ Replace Tags
        // ==============================
        if (request.getTagIds() != null) {

            projectTagRepository.deleteByProjectId(projectId);

            for (Long tagId : request.getTagIds()) {

                ProjectTag pt = new ProjectTag();
                pt.setProjectId(projectId);
                pt.setTagId(tagId);
                pt.setStatus(1);
                pt.setCreatedAt(LocalDateTime.now());

                projectTagRepository.save(pt);
            }
        }

        // ==============================
        // 8️⃣ Replace Advisor
        // ==============================
        if (request.getAdvisorUserId() != null) {

            userRepository.findByIdAndIsActive(request.getAdvisorUserId(), 1)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid advisor"));

            projectAdvisorRepository.deleteByProjectId(projectId);

            ProjectAdvisor advisor = new ProjectAdvisor();
            advisor.setProjectId(projectId);
            advisor.setAdvisorUserId(request.getAdvisorUserId());
            advisor.setStatus(1);
            advisor.setCreatedAt(LocalDateTime.now());

            projectAdvisorRepository.save(advisor);
        }

        // ==============================
        // 9️⃣ Incremental Images
        // ==============================
        if (request.getImages() != null) {

            for (ProjectImageRequest img : request.getImages()) {

                // Remove existing
                if (Boolean.TRUE.equals(img.getRemove()) && img.getImageId() != null) {

                    ProjectImage existing = projectImageRepository
                            .findById(img.getImageId())
                            .orElseThrow(() -> new IllegalArgumentException("Image not found"));

                    existing.setStatus(0);
                    continue;
                }

                // Update sort
                if (img.getImageId() != null && img.getFile() == null) {

                    ProjectImage existing = projectImageRepository
                            .findById(img.getImageId())
                            .orElseThrow(() -> new IllegalArgumentException("Image not found"));

                    existing.setSortOrder(img.getSortOrder());
                    continue;
                }

                // Add new
                if (img.getFile() != null) {

                    String imageUrl = r2StorageService.uploadProjectImage(
                            img.getFile(),
                            projectId);

                    ProjectImage newImage = new ProjectImage();
                    newImage.setProjectId(projectId);
                    newImage.setImageUrl(imageUrl);
                    newImage.setSortOrder(img.getSortOrder());
                    newImage.setStatus(1);
                    newImage.setCreatedAt(LocalDateTime.now());

                    projectImageRepository.save(newImage);
                }
            }
        }

        // ==============================
        // 🔟 Incremental Documents
        // ==============================
        if (request.getDocuments() != null) {

            for (ProjectDocumentRequest doc : request.getDocuments()) {

                if (doc.getFile() != null) {

                    String docUrl = r2StorageService.uploadProjectDocument(
                            doc.getFile(),
                            projectId,
                            doc.getDocumentType());

                    ProjectDocument pd = new ProjectDocument();
                    pd.setProjectId(projectId);
                    pd.setDocumentType(doc.getDocumentType());
                    pd.setDocumentUrl(docUrl);
                    pd.setSortOrder(doc.getSortOrder());
                    pd.setStatus(1);
                    pd.setCreatedAt(LocalDateTime.now());

                    projectDocumentRepository.save(pd);
                }
            }
        }

        // ==============================
        // 1️⃣1️⃣ Incremental Videos
        // ==============================
        if (request.getVideos() != null) {

            for (ProjectVideoRequest v : request.getVideos()) {

                ProjectVideo pv = new ProjectVideo();
                pv.setProjectId(projectId);
                pv.setVideoUrl(v.getVideoUrl());
                pv.setVideoType(v.getVideoType());
                pv.setSortOrder(v.getSortOrder());
                pv.setStatus(1);
                pv.setCreatedAt(LocalDateTime.now());

                projectVideoRepository.save(pv);
            }
        }

        return new ProjectCreateResponse(
                projectId,
                "Project updated successfully");
    }

    ////////// DELETE PROJECT
    ////////// DETAILS/////////////////////////////////////////////////////////

    @Override
    @Transactional
    @CacheEvict(value = "project-cards", allEntries = true)
    public boolean deleteProject(Long projectId, Integer loggedInUserId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid projectId"));

        if (project.getStatus() == 0) {
            return false;
        }

        project.setStatus(0); // Soft delete

        project.setUpdatedBy(loggedInUserId);
        project.setUpdatedAt(LocalDateTime.now());

        projectRepository.save(project);

        return true;
    }

    // Get ProjectBy Id.

    @Override
    @Transactional
    public ProjectDetailResponse getProjectById(Long projectId) {

        Project project = projectRepository
                .findByProjectIdAndStatus(projectId, 1)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        return mapToProjectDetailResponse(project);
    }

    private ProjectDetailResponse mapToProjectDetailResponse(Project project) {

        ProjectDetailResponse response = new ProjectDetailResponse();

        response.setProjectId(project.getProjectId());
        response.setProjectName(project.getProjectName());
        response.setDescription(project.getDescription());
        response.setReraNumber(project.getReraNumber());
        response.setStatus(project.getStatus() != null && project.getStatus() == 1
                ? "ACTIVE"
                : "DELETED");
        response.setSourceType(project.getSourceType() != null ? project.getSourceType().name() : null);
        response.setSourceName(project.getSourceName());

        response.setDeveloperId(project.getDeveloperId());

        // ---------- Unit Types mapping ----------
        List<ProjectUnitType> projectUnitTypes = projectUnitTypeRepository.findByProjectId(project.getProjectId());

        List<ProjectUnitTypeDto> unitTypeDtos = new ArrayList<>();

        for (ProjectUnitType ut : projectUnitTypes) {

            ProjectUnitTypeDto dto = new ProjectUnitTypeDto();
            dto.setProjectUnitTypeId(ut.getProjectUnitTypeId());
            dto.setProjectId(ut.getProjectId());
            dto.setUnitTypeId(ut.getUnitTypeId());
            dto.setSizeSqft(ut.getSizeSqft());
            dto.setPriceMin(ut.getPriceMin());
            dto.setPriceMax(ut.getPriceMax());
            dto.setPriceUnit(ut.getPriceUnit() != null ? ut.getPriceUnit().name() : null);
            dto.setCreatedAt(ut.getCreatedAt());
            dto.setUpdatedAt(ut.getUpdatedAt());

            unitTypeDtos.add(dto);
        }

        response.setUnitTypes(unitTypeDtos);

        // ---------- Amenities mapping ----------
        List<ProjectAmenity> projectAmenities = projectAmenityRepository.findByProjectId(project.getProjectId());

        List<Integer> amenityIds = new ArrayList<>();

        for (ProjectAmenity pa : projectAmenities) {
            amenityIds.add(pa.getAmenityId());
        }

        response.setAmenityIds(amenityIds);

        // ---------- Images mapping ----------
        List<ProjectImage> projectImages = projectImageRepository.findByProjectIdOrderBySortOrderAsc(
                project.getProjectId());

        List<ProjectImageDto> imageDtos = new ArrayList<>();

        for (ProjectImage img : projectImages) {

            ProjectImageDto dto = new ProjectImageDto();
            dto.setImageUrl(buildPublicUrl(img.getImageUrl()));
            dto.setSortOrder(img.getSortOrder());

            imageDtos.add(dto);
        }

        response.setImages(imageDtos);

        // ---------- Brochures mapping ----------
        List<ProjectBrochure> brochures = projectBrochureRepository.findByProjectIdOrderBySortOrderAsc(
                project.getProjectId());

        if (brochures != null && !brochures.isEmpty()) {

            List<ProjectBrochureDto> brochureDtos = new ArrayList<>();

            for (ProjectBrochure brochure : brochures) {

                ProjectBrochureDto dto = new ProjectBrochureDto();
                dto.setBrochureUrl(buildPublicUrl(brochure.getBrochureUrl()));
                dto.setBrochureType(brochure.getBrochureType());

                brochureDtos.add(dto);
            }

            response.setBrochures(brochureDtos);
        }

        // ✅ NEW --- Documents mapping ----------
        List<ProjectDocument> projectDocuments = projectDocumentRepository.findByProjectId(project.getProjectId());

        List<ProjectDocumentDto> documentDtos = new ArrayList<>();

        for (ProjectDocument doc : projectDocuments) {

            if (!Integer.valueOf(1).equals(doc.getStatus()))
                continue; // skip soft-deleted

            ProjectDocumentDto dto = new ProjectDocumentDto();
            dto.setDocumentId(doc.getProjectDocumentId());
            dto.setDocumentType(doc.getDocumentType());
            dto.setDocumentUrl(buildPublicUrl(doc.getDocumentUrl()));
            dto.setSortOrder(doc.getSortOrder());

            documentDtos.add(dto);
        }

        response.setDocuments(documentDtos);

        // ✅ NEW --- Videos mapping ----------
        List<ProjectVideo> projectVideos = projectVideoRepository.findByProjectId(project.getProjectId());

        List<ProjectVideoDto> videoDtos = new ArrayList<>();

        for (ProjectVideo video : projectVideos) {

            if (!Integer.valueOf(1).equals(video.getStatus()))
                continue; // skip soft-deleted

            ProjectVideoDto dto = new ProjectVideoDto();
            dto.setVideoId(video.getProjectVideoId());
            dto.setVideoUrl(video.getVideoUrl());
            dto.setVideoType(video.getVideoType());
            dto.setSortOrder(video.getSortOrder());

            videoDtos.add(dto);
        }

        response.setVideos(videoDtos);

        return response;

    }

    ////////// GetProjectCards//////////////

    @Override
    @Cacheable(value = "project-cards", key = "#loggedInUserId")
    @Transactional(readOnly = true)
    public List<ProjectCardResponse> listProjectCards(Integer loggedInUserId) {

        // 1️⃣ Check if Admin
        boolean admin = isAdmin(loggedInUserId);

        // 2️⃣ Fetch main card data
        List<ProjectCardResponse> cards = admin
                ? projectCardRepository.findAllProjectCards()
                : projectCardRepository.findProjectCards();

        if (cards.isEmpty()) {
            return cards;
        }

        // 2️⃣ FIX COVER IMAGE URL (🔥 KEY FIX 🔥)
        for (ProjectCardResponse card : cards) {

            String rawImagePath = card.getCoverImageUrl();

            if (rawImagePath != null && !rawImagePath.isBlank()) {
                card.setCoverImageUrl(buildPublicUrl(rawImagePath));
            }
        }

        // 3️⃣ Collect project IDs
        List<Long> projectIds = cards.stream()
                .map(ProjectCardResponse::getProjectId)
                .toList();

        // 4️⃣ Fetch BHK types (ONE query)
        List<Object[]> bhkRows = projectUnitTypeRepository.findBhkTypesByProjectIds(projectIds);

        // 5️⃣ Group BHK types (deduplicated)
        Map<Long, Set<Integer>> bhkMap = new HashMap<>(projectIds.size());

        for (Object[] row : bhkRows) {
            Long projectId = (Long) row[0];
            Integer bhk = (Integer) row[1];

            bhkMap
                    .computeIfAbsent(projectId, k -> new HashSet<>())
                    .add(bhk);
        }

        // 6️⃣ Attach sorted BHK list
        for (ProjectCardResponse card : cards) {

            List<Integer> bhks = new ArrayList<>(
                    bhkMap.getOrDefault(card.getProjectId(), Set.of()));

            Collections.sort(bhks);
            card.setBhkTypes(bhks);
        }

        return cards;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectCardResponse> searchProjects(ProjectSearchRequest request, Integer loggedInUserId) {

        // 1️⃣ Determine if admin
        boolean admin = isAdmin(loggedInUserId);

        // 2️⃣ Fetch active projects (Admin sees all, public sees only verified)
        List<Project> projects = admin
                ? projectRepository.findByStatus(1)
                : projectRepository.findByStatusAndIsVerified(1, 1);

        if (projects.isEmpty()) {
            return List.of();
        }

        List<Long> projectIds = projects.stream()
                .map(Project::getProjectId)
                .toList();

        // 2️⃣ Bulk fetch relations to avoid N+1

        Map<Long, List<ProjectUnitType>> unitMap = projectUnitTypeRepository.findByProjectIdIn(projectIds)
                .stream()
                .collect(Collectors.groupingBy(ProjectUnitType::getProjectId));

        Map<Long, Location> locationMap = locationRepository.findByProjectIdIn(projectIds)
                .stream()
                .collect(Collectors.toMap(Location::getProjectId, l -> l));

        Map<Long, ProjectImage> coverImageMap = projectImageRepository.findCoverImagesByProjectIds(projectIds)
                .stream()
                .collect(Collectors.toMap(ProjectImage::getProjectId, i -> i));

        Map<Long, Developer> developerMap = developerRepository.findByDeveloperIdIn(
                projects.stream()
                        .map(Project::getDeveloperId)
                        .filter(Objects::nonNull)
                        .toList())
                .stream()
                .collect(Collectors.toMap(Developer::getDeveloperId, d -> d));

        Map<Integer, ProjectType> projectTypeMap = projectTypeRepository.findAll().stream()
                .collect(Collectors.toMap(ProjectType::getProjectTypeId, pt -> pt));

        Map<Integer, ConstructionStatus> constructionStatusMap = constructionStatusRepository.findByIsActive(1)
                .stream()
                .collect(Collectors.toMap(
                        ConstructionStatus::getConstructionStatusId,
                        cs -> cs));

        // 3️⃣ Apply filters (IN MEMORY but NO DB calls)

        // Developer filter
        if (request.getDeveloperIds() != null && !request.getDeveloperIds().isEmpty()) {
            projects = projects.stream()
                    .filter(p -> request.getDeveloperIds().contains(p.getDeveloperId()))
                    .toList();
        }

        // Project type filter
        if (request.getProjectTypeIds() != null && !request.getProjectTypeIds().isEmpty()) {
            projects = projects.stream()
                    .filter(p -> request.getProjectTypeIds().contains(p.getProjectTypeId()))
                    .toList();
        }

        // RERA filter
        if (request.getReraRegistered() != null) {
            int reraFlag = request.getReraRegistered() ? 1 : 0;
            projects = projects.stream()
                    .filter(p -> p.getReraStatus() != null &&
                            p.getReraStatus() == reraFlag)
                    .toList();
        }

        // Construction status filter
        if (request.getConstructionStatusIds() != null &&
                !request.getConstructionStatusIds().isEmpty()) {

            projects = projects.stream()
                    .filter(p -> request.getConstructionStatusIds()
                            .contains(p.getConstructionStatusid()))
                    .toList();
        }

        // Budget filter (FIXED OVERLAP LOGIC)
        if (request.getMinBudget() != null || request.getMaxBudget() != null) {

            projects = projects.stream()
                    .filter(project -> {

                        List<ProjectUnitType> units = unitMap.getOrDefault(project.getProjectId(), List.of());

                        return units.stream().anyMatch(unit -> {

                            boolean minOk = request.getMinBudget() == null ||
                                    (unit.getPriceMax() != null &&
                                            unit.getPriceMax() >= request.getMinBudget());

                            boolean maxOk = request.getMaxBudget() == null ||
                                    (unit.getPriceMin() != null &&
                                            unit.getPriceMin() <= request.getMaxBudget());

                            return minOk && maxOk;
                        });
                    })
                    .toList();
        }

        // Unit type filter
        if (request.getUnitTypeIds() != null &&
                !request.getUnitTypeIds().isEmpty()) {

            projects = projects.stream()
                    .filter(project -> {
                        List<ProjectUnitType> units = unitMap.getOrDefault(project.getProjectId(), List.of());

                        return units.stream()
                                .anyMatch(u -> request.getUnitTypeIds()
                                        .contains(u.getUnitTypeId()));
                    })
                    .toList();
        }

        // ==============================
        // 8️⃣ Radius filter
        // ==============================
        if (request.getLatitude() != null && request.getLongitude() != null) {

            int radius = request.getRadiusKm() != null
                    ? request.getRadiusKm()
                    : 10;

            projects = projects.stream()
                    .filter(project -> {

                        Location loc = locationMap.get(project.getProjectId());

                        if (loc == null ||
                                loc.getLatitude() == null ||
                                loc.getLongitude() == null) {
                            return false;
                        }

                        double distance = calculateDistanceKm(
                                request.getLatitude(),
                                request.getLongitude(),
                                loc.getLatitude(),
                                loc.getLongitude());

                        return distance <= radius;
                    })
                    .toList();
        }

        // ==============================
        // 9️⃣ Sorting
        // ==============================
        Comparator<Project> comparator;

        String sortBy = request.getSortBy();
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "recent";
        }

        String sortOrder = request.getSortOrder();
        if (sortOrder == null || sortOrder.isBlank()) {
            sortOrder = "desc";
        }

        if ("price".equalsIgnoreCase(sortBy)) {

            comparator = Comparator.comparingDouble(project -> unitMap.getOrDefault(project.getProjectId(), List.of())
                    .stream()
                    .map(ProjectUnitType::getPriceMin)
                    .filter(Objects::nonNull)
                    .min(Double::compareTo)
                    .orElse(Double.MAX_VALUE));

        } else {
            comparator = Comparator.comparing(Project::getCreatedAt);
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        projects = projects.stream()
                .sorted(comparator)
                .toList();

        // ==============================
        // 🔟 Build cards
        // ==============================
        return projects.stream()
                .map((Project project) -> buildProjectCard(
                        project,
                        unitMap,
                        locationMap,
                        coverImageMap,
                        developerMap,
                        projectTypeMap,
                        constructionStatusMap))
                .collect(Collectors.toList());
    }

    // ==============================
    // Haversine Distance Calculation
    // ==============================
    private double calculateDistanceKm(
            double lat1, double lon1,
            double lat2, double lon2) {

        final int EARTH_RADIUS = 6371; // km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectCardResponse> listUnverifiedProjectCards() {

        List<ProjectCardResponse> cards = projectCardRepository.findUnverifiedProjectCards();

        if (cards.isEmpty()) {
            return cards;
        }

        for (ProjectCardResponse card : cards) {
            String rawImagePath = card.getCoverImageUrl();
            if (rawImagePath != null && !rawImagePath.isBlank()) {
                card.setCoverImageUrl(buildPublicUrl(rawImagePath));
            }
        }

        List<Long> projectIds = cards.stream()
                .map(ProjectCardResponse::getProjectId)
                .toList();

        List<Object[]> bhkRows = projectUnitTypeRepository.findBhkTypesByProjectIds(projectIds);
        Map<Long, Set<Integer>> bhkMap = new HashMap<>(projectIds.size());

        for (Object[] row : bhkRows) {
            Long projectId = (Long) row[0];
            Integer bhk = (Integer) row[1];
            bhkMap.computeIfAbsent(projectId, k -> new HashSet<>()).add(bhk);
        }

        for (ProjectCardResponse card : cards) {
            List<Integer> bhks = new ArrayList<>(bhkMap.getOrDefault(card.getProjectId(), Set.of()));
            Collections.sort(bhks);
            card.setBhkTypes(bhks);
        }

        return cards;
    }

    @Override
    @Transactional
    @CacheEvict(value = "project-cards", allEntries = true)
    public ProjectCreateResponse verifyProject(Long projectId, Integer adminUserId) {

        Project project = projectRepository.findByProjectIdAndStatus(projectId, 1)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        project.setIsVerified(1);
        project.setUpdatedBy(adminUserId);
        project.setUpdatedAt(LocalDateTime.now());

        projectRepository.save(project);

        return new ProjectCreateResponse(projectId, "Project verified and published successfully");
    }

    private ProjectCardResponse buildProjectCard(
            Project project,
            Map<Long, List<ProjectUnitType>> unitMap,
            Map<Long, Location> locationMap,
            Map<Long, ProjectImage> coverImageMap,
            Map<Long, Developer> developerMap,
            Map<Integer, ProjectType> projectTypeMap,
            Map<Integer, ConstructionStatus> constructionStatusMap) {

        ProjectCardResponse card = new ProjectCardResponse();

        card.setProjectId(project.getProjectId());
        card.setProjectName(project.getProjectName());

        // Developer
        Developer dev = developerMap.get(project.getDeveloperId());
        if (dev != null) {
            card.setDeveloperName(dev.getDeveloperName());
        }

        // RERA
        card.setReraStatus(project.getReraStatus() != null &&
                project.getReraStatus() == 1);

        // Construction Status
        ConstructionStatus status = constructionStatusMap.get(project.getConstructionStatusid());
        if (status != null) {
            card.setConstructionStatus(status.getLabel());
        }

        // Project Type
        ProjectType pt = projectTypeMap.get(project.getProjectTypeId());
        if (pt != null) {
            card.setProjectType(pt.getName());
        }

        // Possession
        card.setPossessionDate(project.getPossessionDate());

        // Location
        Location loc = locationMap.get(project.getProjectId());
        if (loc != null) {
            card.setCity(loc.getCity());
            card.setArea(loc.getArea());
        }

        // Units & Pricing
        List<ProjectUnitType> units = unitMap.getOrDefault(project.getProjectId(), List.of());

        Double minPrice = null;
        Double maxPrice = null;
        String priceUnit = null;
        List<Integer> bhkTypes = new ArrayList<>();

        for (ProjectUnitType ut : units) {

            bhkTypes.add(ut.getUnitTypeId());

            if (ut.getPriceMin() != null)
                minPrice = (minPrice == null)
                        ? ut.getPriceMin()
                        : Math.min(minPrice, ut.getPriceMin());

            if (ut.getPriceMax() != null)
                maxPrice = (maxPrice == null)
                        ? ut.getPriceMax()
                        : Math.max(maxPrice, ut.getPriceMax());

            priceUnit = ut.getPriceUnit() != null ? ut.getPriceUnit().name() : null;
        }

        card.setMinPrice(minPrice);
        card.setMaxPrice(maxPrice);
        card.setPriceUnit(priceUnit);
        card.setBhkTypes(bhkTypes);

        // Cover Image
        ProjectImage cover = coverImageMap.get(project.getProjectId());
        if (cover != null) {
            card.setCoverImageUrl(buildPublicUrl(cover.getImageUrl()));
        }

        return card;
    }
}
