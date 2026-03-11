package com.sthi.re.dto.response;

public class PropertyCreateResponse {

    private Long propertyId;
    private String message;

    public PropertyCreateResponse(Long propertyId, String message) {
        this.propertyId = propertyId;
        this.message = message;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public String getMessage() {
        return message;
    }
}
