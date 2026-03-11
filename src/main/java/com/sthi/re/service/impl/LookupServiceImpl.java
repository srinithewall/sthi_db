package com.sthi.re.service.impl;

import com.sthi.re.dto.response.LookupDto;
import com.sthi.re.model.Amenity;
import com.sthi.re.model.Developer;
import com.sthi.re.model.Tag;
import com.sthi.re.repo.*;
import com.sthi.re.service.LookupService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {

    private final DeveloperRepository developerRepository;
    private final ProjectTypeRepository projectTypeRepository;
    private final UnitTypeRepository unitTypeRepository;
    private final AmenityRepository amenityRepository;
    private final ConstructionStatusRepository constructionStatusRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public LookupServiceImpl(
            DeveloperRepository developerRepository,
            ProjectTypeRepository projectTypeRepository,
            UnitTypeRepository unitTypeRepository,
            AmenityRepository amenityRepository,
            ConstructionStatusRepository constructionStatusRepository,
            UserRepository userRepository,
            TagRepository tagRepository
    ) {
        this.developerRepository = developerRepository;
        this.projectTypeRepository = projectTypeRepository;
        this.unitTypeRepository = unitTypeRepository;
        this.amenityRepository = amenityRepository;
        this.constructionStatusRepository = constructionStatusRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<LookupDto> getActiveDevelopers() {
        return developerRepository.findAll()
                .stream()
                .map(d -> new LookupDto(
                        d.getDeveloperId(), // already Long
                        d.getDeveloperName()
                ))
                .toList();
    }

    @Override
    public List<LookupDto> getProjectTypes() {
        return projectTypeRepository.findAll()
                .stream()
                .map(p -> new LookupDto(
                        Long.valueOf(p.getProjectTypeId()),  // FIX
                        p.getName()
                ))
                .toList();
    }

    @Override
    public List<LookupDto> getUnitTypes() {
        return unitTypeRepository.findAll()
                .stream()
                .map(u -> new LookupDto(
                        Long.valueOf(u.getUnitTypeId()),  // FIX
                        u.getName()
                ))
                .toList();
    }

    @Override
    public List<LookupDto> getAmenities() {
        return amenityRepository.findAll()
                .stream()
                .map(a -> new LookupDto(
                        Long.valueOf(a.getAmenityId()),  // FIX
                        a.getName()
                ))
                .toList();
    }

    @Override
    public List<LookupDto> getConstructionStatuses() {
        return constructionStatusRepository.findAll()
                .stream()
                .map(s -> new LookupDto(
                        Long.valueOf(s.getConstructionStatusId()),  // FIX
                        s.getLabel()
                ))
                .toList();
    }

    @Override
    public List<LookupDto> getAdvisors() {

        return userRepository
                .findByUserTypeIdAndIsActive(2L, 1)
                .stream()
                .map(u -> new LookupDto(
                        Long.valueOf(u.getId()),   // convert int → Long
                        u.getFirstName() + " " + u.getLastName()
                ))
                .toList();
    }

    @Override
    public List<LookupDto> getTagsByType(String type) {
        return tagRepository
                .findByTagTypeAndIsActiveTrueOrderByDisplayOrderAsc(type.toUpperCase())
                .stream()
                .map(tag -> new LookupDto(
                        tag.getTagId(),
                        tag.getTagName()
                ))
                .toList();
    }

    @Override
    public LookupDto addDeveloper(String developerName) {
        Developer developer = new Developer();
        developer.setDeveloperName(developerName);
        developer.setStatus(1);
        developer.setCreatedAt(LocalDateTime.now());
        Developer saved = developerRepository.save(developer);
        return new LookupDto(saved.getDeveloperId(), saved.getDeveloperName());
    }

    @Override
    public LookupDto addAmenity(String amenityName) {
        Amenity amenity = new Amenity();
        amenity.setName(amenityName);
        amenity.setStatus(1);
        amenity.setCreatedAt(LocalDateTime.now());
        Amenity saved = amenityRepository.save(amenity);
        return new LookupDto(Long.valueOf(saved.getAmenityId()), saved.getName());
    }
}