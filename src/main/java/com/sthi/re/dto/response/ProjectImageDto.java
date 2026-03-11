package com.sthi.re.dto.response;

public class ProjectImageDto {

    private String imageUrl;
    private Integer sortOrder;

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    // setters (REQUIRED)
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
