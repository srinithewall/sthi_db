package com.sthi.re.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sthi.re.dto.request.CreateLeadRequest;
import com.sthi.re.dto.response.CreateLeadResponse;
import com.sthi.re.model.ReLead;
import com.sthi.re.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping("/request-callback")
    public ResponseEntity<CreateLeadResponse> requestCallback(
            @Valid @RequestBody CreateLeadRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) Integer loggedInUserId,
            HttpServletRequest httpRequest
    ) {

        String ipAddress = extractClientIp(httpRequest);

        ReLead lead = leadService.createLead(
                request.getFullName(),
                request.getPhone(),
                request.getEmail(),
                request.getMessage(),
                request.getProjectId(),
                request.getPropertyId(),  // 👈 important
                loggedInUserId,
                ipAddress,
                request.getRecaptchaToken()
        );

        return ResponseEntity.ok(
                new CreateLeadResponse(
                        "SUCCESS",
                        "Thank you for your interest! Our property advisor will contact you shortly."
                )
        );
    }

    private String extractClientIp(HttpServletRequest request) {

        String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader != null && !xfHeader.isBlank()) {
            return xfHeader.split(",")[0];
        }

        return request.getRemoteAddr();
    }
}