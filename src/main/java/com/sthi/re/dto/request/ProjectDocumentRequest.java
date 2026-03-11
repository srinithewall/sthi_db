package com.sthi.re.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ProjectDocumentRequest {

    private MultipartFile file;


    private String documentType;

    private Integer sortOrder;

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
}
