package com.sthi.signature.controller;

import com.sthi.signature.dto.AgreementRequest;
import com.sthi.signature.dto.AgreementStatusResponse;
import com.sthi.signature.entity.Agreement;
import com.sthi.signature.service.AgreementService;
import com.sthi.signature.service.RentalAgreementService;
import com.sthi.signature.service.extraction.KycExtractionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/signature/rental")
@RequiredArgsConstructor
public class RentalAgreementController {

    private final RentalAgreementService rentalAgreementService;
    private final AgreementService agreementService;
    private final ApplicationContext applicationContext;
    private final Environment env;

    @GetMapping("/agreements")
    public ResponseEntity<java.util.List<Agreement>> listAgreements() {
        return ResponseEntity.ok(agreementService.getAllAgreements());
    }

    // Dynamically fetch the KYC provider based on config property
    private KycExtractionService getKycProvider() {
        String provider = env.getProperty("kyc.extraction.provider", "openai");
        return applicationContext.getBean(provider, KycExtractionService.class);
    }

    @PostMapping("/initiate")
    public ResponseEntity<Agreement> initiateAgreement(@RequestBody AgreementRequest request) {
        log.info("Initiating Agreement for template {}", request.getTemplateId());
        return ResponseEntity.ok(agreementService.createAgreement(request));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<AgreementStatusResponse> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(rentalAgreementService.getStatus(id));
    }

    @PostMapping("/{partyType}/kyc")
    public ResponseEntity<KycExtractedData> uploadKyc(
            @RequestParam("agreementId") Long agreementId,
            @PathVariable String partyType,
            @RequestParam("file") MultipartFile file,
            @RequestParam("docType") String docType) {

        log.info("Received KYC Upload: Agreement {}, Party {}, Doc {}", agreementId, partyType, docType);
        KycExtractionService extractionService = getKycProvider();

        KycExtractedData data = rentalAgreementService.uploadAndExtractKyc(agreementId, partyType.toUpperCase(), file,
                docType, extractionService);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generateDraft(@RequestParam("agreementId") Long agreementId) {
        log.info("Generating Draft PDF for Agreement {}", agreementId);
        String draftPdfPath = rentalAgreementService.generateDraft(agreementId);

        Map<String, String> response = new HashMap<>();
        response.put("agreementId", agreementId.toString());
        response.put("draftPdfPath", draftPdfPath); // In production this would be a pre-signed S3 URL

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/send-otp")
    public ResponseEntity<Map<String, String>> requestOtp(@PathVariable Long id, @RequestBody SigningRequest request) {
        log.info("Requesting OTP for Agreement {}, Party {}", id, request.getPartyType());
        rentalAgreementService.requestOtp(id, request.getPartyType(), request.getMobile());

        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP Sent successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@PathVariable Long id, @RequestBody SigningRequest request) {
        log.info("Verifying OTP for Agreement {}, Party {}", id, request.getPartyType());
        String token = rentalAgreementService.verifyOtp(id, request.getPartyType(), request.getOtp());

        Map<String, String> response = new HashMap<>();
        response.put("signing_token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/sign")
    public ResponseEntity<Map<String, String>> submitSignature(
            @PathVariable Long id,
            @RequestBody SigningRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            HttpServletRequest servletRequest) {

        log.info("Submitting signature for Agreement {}, Party {}", id, request.getPartyType());

        // Basic JWT present check (real validation should be in a generic filter, but
        // doing basic check here for POC)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Missing or invalid signing token"));
        }

        String ipAddress = servletRequest.getRemoteAddr();
        rentalAgreementService.signAgreement(id, request.getPartyType(), ipAddress);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Digital signature applied successfully");
        return ResponseEntity.ok(response);
    }
}
