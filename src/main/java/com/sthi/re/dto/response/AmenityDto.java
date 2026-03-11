package com.sthi.re.dto.response;

import java.time.LocalDateTime;

public class AmenityDto {

    private Integer amenityId;
    private String name;
    private String icon;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getAmenityId() {
        return amenityId;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
