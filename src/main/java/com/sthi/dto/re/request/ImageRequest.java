// ImageRequest.java
package com.sthi.dto.re.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Add 'public' modifier
public class ImageRequest {
    
    @NotBlank(message = "Image URL is required")
    @Size(max = 1000, message = "Image URL cannot exceed 1000 characters")
    private String url;
    
    @JsonProperty("is_primary")
    private Boolean isPrimary = false;

    // Add getters and setters if not using Lombok
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
}