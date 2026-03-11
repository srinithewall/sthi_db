// Location.java
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
public class Location {
    
    @JsonProperty("location_id")
    private Long locationId;
    
    private String city;
    
    private String zone;
    
    private String area;
    
    private String state;
    
    private String country;
    
    @JsonProperty("display_name")
    public String getDisplayName() {
        return String.format("%s, %s, %s", area, city, state);
    }
}