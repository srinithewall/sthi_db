package com.sthi.re.service;

import com.sthi.re.dto.response.LookupDto;
import java.util.List;

public interface LookupService {

    List<LookupDto> getActiveDevelopers();
    List<LookupDto> getProjectTypes();
    List<LookupDto> getUnitTypes();
    List<LookupDto> getAmenities();
    List<LookupDto> getConstructionStatuses();
    List<LookupDto> getAdvisors();
    List<LookupDto> getTagsByType(String type);

    LookupDto addDeveloper(String developerName);
    LookupDto addAmenity(String amenityName);
}
