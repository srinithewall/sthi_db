package com.sthi.model.re;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectImage {
    
    @JsonProperty("image_id")
    private Long imageId;
    
    @JsonProperty("project_id")
    private Long projectId;
    
    @JsonProperty("image_url")
    private String imageUrl;
    
    @JsonProperty("is_primary")
    private Boolean isPrimary;
}