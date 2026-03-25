package com.sthi.re.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ProjectImageRequest {

    /**
     * Existing image ID (used during update)
     * If null → new image
     */
    private Long imageId;

    /**
     * New file upload (only for new images)
     */
    private MultipartFile file;

    /**
     * Sort order of image
     */
    private Integer sortOrder;

    /**
     * If true → soft delete existing image
     */
    private Boolean remove;

    private String imageType;
    private Integer fileSize;

    // =========================
    // Getters & Setters
    // =========================

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
}