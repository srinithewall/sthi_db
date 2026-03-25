package com.sthi.re.dto.request;

import java.time.LocalDate;
import java.util.List;

public class UpdateProjectRequest {

    // ---------- Core ----------
    private String projectName;
    private String description;
    private String reraNumber;
    private Integer constructionStatusid;
    private LocalDate possessionDate;
    private String websiteUrl;
    private Boolean isVerified;
    private String sourceType;
    private String sourceName;
    private String sourceUpdatedAt;

    // ---------- Location ----------
    private LocationRequest location;

    // ---------- Units ----------
    private List<ProjectUnitTypeRequest> unitTypes;

    // ---------- Amenities ----------
    private List<Integer> amenityIds;

    // ---------- Advisor ----------
    private Integer advisorUserId;

    // ---------- Images ----------
    private List<ProjectImageRequest> images;

    // ---------- Documents ----------
    private List<ProjectDocumentRequest> documents;

    // ---------- Videos ----------
    private List<ProjectVideoRequest> videos;

    // ---------- Tags ----------
    private List<Long> tagIds;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReraNumber() {
		return reraNumber;
	}

	public void setReraNumber(String reraNumber) {
		this.reraNumber = reraNumber;
	}

	public Integer getConstructionStatusid() {
		return constructionStatusid;
	}

	public void setConstructionStatusid(Integer constructionStatusid) {
		this.constructionStatusid = constructionStatusid;
	}

	public LocalDate getPossessionDate() {
		return possessionDate;
	}

	public void setPossessionDate(LocalDate possessionDate) {
		this.possessionDate = possessionDate;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceName() {
		return sourceName;
	}

	    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUpdatedAt() {
        return sourceUpdatedAt;
    }

    public void setSourceUpdatedAt(String sourceUpdatedAt) {
        this.sourceUpdatedAt = sourceUpdatedAt;
    }

	public LocationRequest getLocation() {
		return location;
	}

	public void setLocation(LocationRequest location) {
		this.location = location;
	}

	public List<ProjectUnitTypeRequest> getUnitTypes() {
		return unitTypes;
	}

	public void setUnitTypes(List<ProjectUnitTypeRequest> unitTypes) {
		this.unitTypes = unitTypes;
	}

	public List<Integer> getAmenityIds() {
		return amenityIds;
	}

	public void setAmenityIds(List<Integer> amenityIds) {
		this.amenityIds = amenityIds;
	}

	public Integer getAdvisorUserId() {
		return advisorUserId;
	}

	public void setAdvisorUserId(Integer advisorUserId) {
		this.advisorUserId = advisorUserId;
	}

	public List<ProjectImageRequest> getImages() {
		return images;
	}

	public void setImages(List<ProjectImageRequest> images) {
		this.images = images;
	}

	public List<ProjectDocumentRequest> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ProjectDocumentRequest> documents) {
		this.documents = documents;
	}

	public List<ProjectVideoRequest> getVideos() {
		return videos;
	}

	public void setVideos(List<ProjectVideoRequest> videos) {
		this.videos = videos;
	}

	public List<Long> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Long> tagIds) {
		this.tagIds = tagIds;
	}

    // getters & setters
}
