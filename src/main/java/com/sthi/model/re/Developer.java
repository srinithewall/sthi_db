// Developer.java - As DTO (remove JPA annotations)
package com.sthi.model.re;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Developer {
    
    @JsonProperty("developer_id")
    private Long developerId;
    
    @JsonProperty("developer_name")
    private String developerName;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("website_url")
    private String websiteUrl;
    
    @JsonProperty("contact_email")
    private String contactEmail;
    
    @JsonProperty("contact_phone")
    private String contactPhone;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}