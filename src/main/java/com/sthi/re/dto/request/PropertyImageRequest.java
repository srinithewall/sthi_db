package com.sthi.re.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PropertyImageRequest {

    // Existing image ID (used during update)
    private Long propertyImageId;

    // New file (used when uploading new image)
    private MultipartFile file;

    // Sorting priority
    private Integer sortOrder;

    // Used in update to remove specific image
    private Boolean remove;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getPropertyImageId() {
        return propertyImageId;
    }

    public void setPropertyImageId(Long propertyImageId) {
        this.propertyImageId = propertyImageId;
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
}