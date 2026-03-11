// Amenity.java
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
public class Amenity {
    
    @JsonProperty("amenity_id")
    private Long amenityId;
    
    @JsonProperty("amenity_name")
    private String amenityName;
}