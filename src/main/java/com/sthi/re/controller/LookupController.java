package com.sthi.re.controller;

import com.sthi.re.dto.response.LookupDto;
import com.sthi.re.service.LookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lookups")
public class LookupController {

    private final LookupService lookupService;

    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/developers")
    public ResponseEntity<List<LookupDto>> getDevelopers() {
        return ResponseEntity.ok(lookupService.getActiveDevelopers());
    }

    @GetMapping("/project-types")
    public ResponseEntity<List<LookupDto>> getProjectTypes() {
        return ResponseEntity.ok(lookupService.getProjectTypes());
    }

    @GetMapping("/unit-types")
    public ResponseEntity<List<LookupDto>> getUnitTypes() {
        return ResponseEntity.ok(lookupService.getUnitTypes());
    }

    @GetMapping("/amenities")
    public ResponseEntity<List<LookupDto>> getAmenities() {
        return ResponseEntity.ok(lookupService.getAmenities());
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<LookupDto>> getConstructionStatuses() {
        return ResponseEntity.ok(lookupService.getConstructionStatuses());
    }

    @GetMapping("/advisors")
    public ResponseEntity<List<LookupDto>> getAdvisors() {
        return ResponseEntity.ok(lookupService.getAdvisors());
    }
    
    @GetMapping("/tags/{type}")
    public ResponseEntity<List<LookupDto>> getTagsByType(@PathVariable String type) {
        return ResponseEntity.ok(lookupService.getTagsByType(type));
    }

    @PostMapping("/developers")
    public ResponseEntity<LookupDto> addDeveloper(@RequestBody java.util.Map<String, String> payload) {
        String name = payload.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lookupService.addDeveloper(name.trim()));
    }

    @PostMapping("/amenities")
    public ResponseEntity<LookupDto> addAmenity(@RequestBody java.util.Map<String, String> payload) {
        String name = payload.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lookupService.addAmenity(name.trim()));
    }
}
