package com.sthi.model.re;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {
    
    @JsonProperty("project_id")
    private Long projectId;
    
    @JsonProperty("developer_id")
    private Long developerId;
    
    @JsonProperty("type_id")
    private Long typeId;
    
    @JsonProperty("location_id")
    private Long locationId;
    
    @JsonProperty("project_name")
    private String projectName;
    
    @JsonProperty("developer_name")
    private String developerName;
    
    @JsonProperty("property_type")
    private String propertyType;
    
    @JsonProperty("location_area")
    private String locationArea;
    
    private String status;
    
    @JsonProperty("price_min")
    private BigDecimal priceMin;
    
    @JsonProperty("price_max")
    private BigDecimal priceMax;
    
    private String description;
    
    @JsonProperty("cashback_offer")
    private String cashbackOffer;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;
    
    @JsonProperty("rera_number")
    private String reraNumber;
    
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonProperty("images")
    @Builder.Default
    private List<ProjectImage> images = new ArrayList<>();
    
    @JsonProperty("unit_types")
    @Builder.Default
    private List<UnitType> unitTypes = new ArrayList<>();
    
    @JsonProperty("amenities")
    @Builder.Default
    private List<String> amenities = new ArrayList<>();
    
    @JsonProperty("location_info")
    private Location locationInfo;
    
    // Helper method to get status display name
    public static String getStatusDisplayName(String status) {
        if (status == null) return "";
        
        switch (status.toUpperCase().replace(" ", "_")) {
            case "NEW_ARRIVAL": return "New Arrival";
            case "TRENDING": return "Trending";
            case "READY_TO_MOVE": return "Ready to Move";
            case "UNDER_CONSTRUCTION": return "Under Construction";
            default: return status;
        }
    }
}