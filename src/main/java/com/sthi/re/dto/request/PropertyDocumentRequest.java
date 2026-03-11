package com.sthi.re.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PropertyDocumentRequest {

    // Existing document ID (for update operations)
    private Long propertyDocumentId;

    // New file (for upload)
    private MultipartFile file;

    // Document type (PHODI, RTC, EC, SALE_DEED, TAX_RECEIPT, OTHER)
    private String documentType;

    // Display priority
    private Integer sortOrder;

    // Mark for removal during update
    private Boolean remove;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getPropertyDocumentId() {
        return propertyDocumentId;
    }

    public void setPropertyDocumentId(Long propertyDocumentId) {
        this.propertyDocumentId = propertyDocumentId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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