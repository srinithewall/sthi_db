package com.sthi.dto.re.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationResponse {
    
    @JsonProperty("location_id")
    private Long locationId;
    
    private String city;
    
    private String zone;
    
    private String area;
    
    private String state;
    
    private String country;
    
    // Constructors
    public LocationResponse() {
    }
    
    public LocationResponse(Long locationId, String city, String zone, String area, 
                          String state, String country) {
        this.locationId = locationId;
        this.city = city;
        this.zone = zone;
        this.area = area;
        this.state = state;
        this.country = country;
    }
    
    // Builder method
    public static LocationResponseBuilder builder() {
        return new LocationResponseBuilder();
    }
    
    // Getters and Setters
    public Long getLocationId() {
        return locationId;
    }
    
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    @JsonProperty("display_name")
    public String getDisplayName() {
        return String.format("%s, %s, %s", area, city, state);
    }
    
    // Builder class
    public static class LocationResponseBuilder {
        private Long locationId;
        private String city;
        private String zone;
        private String area;
        private String state;
        private String country;
        
        public LocationResponseBuilder locationId(Long locationId) {
            this.locationId = locationId;
            return this;
        }
        
        public LocationResponseBuilder city(String city) {
            this.city = city;
            return this;
        }
        
        public LocationResponseBuilder zone(String zone) {
            this.zone = zone;
            return this;
        }
        
        public LocationResponseBuilder area(String area) {
            this.area = area;
            return this;
        }
        
        public LocationResponseBuilder state(String state) {
            this.state = state;
            return this;
        }
        
        public LocationResponseBuilder country(String country) {
            this.country = country;
            return this;
        }
        
        public LocationResponse build() {
            return new LocationResponse(locationId, city, zone, area, state, country);
        }
    }
}