package com.sthi.dto.re.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeveloperResponse {
    
    @JsonProperty("developer_id")
    private Long developerId;
    
    @JsonProperty("developer_name")
    private String developerName;
    
    private String description;
    
    @JsonProperty("website_url")
    private String websiteUrl;
    
    @JsonProperty("contact_email")
    private String contactEmail;
    
    @JsonProperty("contact_phone")
    private String contactPhone;
    
    // Constructors
    public DeveloperResponse() {
    }
    
    public DeveloperResponse(Long developerId, String developerName, String description, 
                           String websiteUrl, String contactEmail, String contactPhone) {
        this.developerId = developerId;
        this.developerName = developerName;
        this.description = description;
        this.websiteUrl = websiteUrl;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }
    
    // Builder method
    public static DeveloperResponseBuilder builder() {
        return new DeveloperResponseBuilder();
    }
    
    // Getters and Setters
    public Long getDeveloperId() {
        return developerId;
    }
    
    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }
    
    public String getDeveloperName() {
        return developerName;
    }
    
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getWebsiteUrl() {
        return websiteUrl;
    }
    
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    // Builder class
    public static class DeveloperResponseBuilder {
        private Long developerId;
        private String developerName;
        private String description;
        private String websiteUrl;
        private String contactEmail;
        private String contactPhone;
        
        public DeveloperResponseBuilder developerId(Long developerId) {
            this.developerId = developerId;
            return this;
        }
        
        public DeveloperResponseBuilder developerName(String developerName) {
            this.developerName = developerName;
            return this;
        }
        
        public DeveloperResponseBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public DeveloperResponseBuilder websiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }
        
        public DeveloperResponseBuilder contactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }
        
        public DeveloperResponseBuilder contactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }
        
        public DeveloperResponse build() {
            return new DeveloperResponse(developerId, developerName, description, 
                                       websiteUrl, contactEmail, contactPhone);
        }
    }
}