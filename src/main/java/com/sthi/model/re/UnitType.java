package com.sthi.model.re;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitType {
    
    @JsonProperty("unit_id")
    private Long unitId;
    
    @JsonProperty("project_id")
    private Long projectId;
    
    @JsonProperty("unit_name")
    private String unitName;
    
    @JsonProperty("size_sqft")
    private BigDecimal sizeSqft;
    
    private BigDecimal price;
}