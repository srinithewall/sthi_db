// UnitTypeRequest.java
package com.sthi.dto.re.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

// Add 'public' modifier
public class UnitTypeRequest {
    
    @NotBlank(message = "Unit name is required")
    @Size(max = 100, message = "Unit name cannot exceed 100 characters")
    @JsonProperty("unit_name")
    private String unitName;
    
    @NotNull(message = "Size in sqft is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Size must be greater than 0")
    @JsonProperty("size_sqft")
    private BigDecimal sizeSqft;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    // Add getters and setters if not using Lombok
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
}