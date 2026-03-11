package com.sthi.re.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ProjectBrochureRequest {

    private MultipartFile file;
    private String brochureType; // PDF, FLOOR_PLAN, MASTER_PLAN
    private Integer sortOrder;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getBrochureType() {
        return brochureType;
    }

    public void setBrochureType(String brochureType) {
        this.brochureType = brochureType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
