package com.sthi.dto.re.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitTypeResponse {
    
    @JsonProperty("unit_id")
    private Long unitId;
    
    @JsonProperty("project_id")
    private Long projectId;
    
    @JsonProperty("unit_name")
    private String unitName;
    
    @JsonProperty("size_sqft")
    private BigDecimal sizeSqft;
    
    private BigDecimal price;
    
    // Constructors
    public UnitTypeResponse() {
    }
    
    public UnitTypeResponse(Long unitId, Long projectId, String unitName, 
                          BigDecimal sizeSqft, BigDecimal price) {
        this.unitId = unitId;
        this.projectId = projectId;
        this.unitName = unitName;
        this.sizeSqft = sizeSqft;
        this.price = price;
    }
    
    // Builder method
    public static UnitTypeResponseBuilder builder() {
        return new UnitTypeResponseBuilder();
    }
    
    // Getters and Setters
    public Long getUnitId() {
        return unitId;
    }
    
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
    
    public Long getProjectId() {
        return projectId;
    }
    
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    public String getUnitName() {
        return unitName;
    }
    
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
    public BigDecimal getSizeSqft() {
        return sizeSqft;
    }
    
    public void setSizeSqft(BigDecimal sizeSqft) {
        this.sizeSqft = sizeSqft;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    // Builder class
    public static class UnitTypeResponseBuilder {
        private Long unitId;
        private Long projectId;
        private String unitName;
        private BigDecimal sizeSqft;
        private BigDecimal price;
        
        public UnitTypeResponseBuilder unitId(Long unitId) {
            this.unitId = unitId;
            return this;
        }
        
        public UnitTypeResponseBuilder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }
        
        public UnitTypeResponseBuilder unitName(String unitName) {
            this.unitName = unitName;
            return this;
        }
        
        public UnitTypeResponseBuilder sizeSqft(BigDecimal sizeSqft) {
            this.sizeSqft = sizeSqft;
            return this;
        }
        
        public UnitTypeResponseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public UnitTypeResponse build() {
            return new UnitTypeResponse(unitId, projectId, unitName, sizeSqft, price);
        }
    }
}