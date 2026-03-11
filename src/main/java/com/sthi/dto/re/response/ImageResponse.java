package com.sthi.dto.re.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageResponse {
    
    @JsonProperty("image_id")
    private Long imageId;
    
    @JsonProperty("project_id")
    private Long projectId;
    
    @JsonProperty("url")
    private String imageUrl;
    
    @JsonProperty("is_primary")
    private Boolean isPrimary;
    
    // Constructors
    public ImageResponse() {
    }
    
    public ImageResponse(Long imageId, Long projectId, String imageUrl, Boolean isPrimary) {
        this.imageId = imageId;
        this.projectId = projectId;
        this.imageUrl = imageUrl;
        this.isPrimary = isPrimary;
    }
    
    // Builder method
    public static ImageResponseBuilder builder() {
        return new ImageResponseBuilder();
    }
    
    // Getters and Setters
    public Long getImageId() {
        return imageId;
    }
    
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
    
    public Long getProjectId() {
        return projectId;
    }
    
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Boolean getIsPrimary() {
        return isPrimary;
    }
    
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
    
    // Builder class
    public static class ImageResponseBuilder {
        private Long imageId;
        private Long projectId;
        private String imageUrl;
        private Boolean isPrimary;
        
        public ImageResponseBuilder imageId(Long imageId) {
            this.imageId = imageId;
            return this;
        }
        
        public ImageResponseBuilder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }
        
        public ImageResponseBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public ImageResponseBuilder isPrimary(Boolean isPrimary) {
            this.isPrimary = isPrimary;
            return this;
        }
        
        public ImageResponse build() {
            return new ImageResponse(imageId, projectId, imageUrl, isPrimary);
        }
    }
}