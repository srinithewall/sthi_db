package com.sthi.signature.service;

import com.sthi.signature.dto.AgreementStatusResponse;
import com.sthi.signature.dto.KycExtractedData;
import com.sthi.signature.entity.Agreement;
import com.sthi.signature.entity.AgreementTemplate;
import com.sthi.signature.entity.KycDocument;
import com.sthi.signature.repository.AgreementRepository;
import com.sthi.signature.repository.AgreementTemplateRepository;
import com.sthi.signature.repository.KycDocumentRepository;
import com.sthi.signature.service.extraction.KycExtractionService;
import com.sthi.signature.util.HashUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalAgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementTemplateRepository templateRepository;
    private final KycDocumentRepository kycDocumentRepository;
    private final TemplateService templateService;
    private final PdfGenerationService pdfGenerationService;
    private final OtpService otpService;

    // We get the KYC service dynamically via config in the Controller, but we can
    // also wire it here.
    // However, if we need it dynamically selected per request, standard Spring
    // handles that by naming.
    // We'll inject the bean via qualifier dynamically passed from the controller or
    // configured globally.

    @Value("${kyc.upload.base-path:./uploads/kyc}")
    private String uploadBasePath;

    @Value("${app.jwtSecret:abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+/}")
    private String jwtSecret;

    @Transactional
    public KycExtractedData uploadAndExtractKyc(Long agreementId, String partyType, MultipartFile file,
            String docType, KycExtractionService extractionService) {

        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));

        try {
            // 1. Save File to Disk
            String folderPath = uploadBasePath + "/" + agreementId;
            File folder = new File(folderPath);
            folder.mkdirs();

            String filename = partyType + "_" + docType + "_" + System.currentTimeMillis() + ".jpg";
            String fullPath = folderPath + "/" + filename;
            file.transferTo(new File(fullPath));

            // 2. Extract Data via AI/OCR
            KycExtractedData data = extractionService.extractFromImage(file, docType);

            // 3. Save Record
            KycDocument doc = KycDocument.builder()
                    .agreement(agreement)
                    .partyType(partyType)
                    .docType(docType)
                    .filePath(fullPath)
                    .extractedName(data.getFullName())
                    .extractedDob(data.getDateOfBirth())
                    .extractedGender(data.getGender())
                    .extractedAddress(data.getAddress())
                    .extractedPincode(data.getPincode())
                    .aadhaarNumber(data.getAadhaarNumber())
                    .panNumber(data.getPanNumber())
                    .fatherName(data.getFatherName())
                    .confidenceScore(data.getConfidenceScore())
                    .rawAiResponse(data.getRawAiResponse())
                    .extractionStatus("SUCCESS")
                    .extractedAt(LocalDateTime.now())
                    .build();

            kycDocumentRepository.save(doc);

            // Update Agreement KYC status (simplified logic for POC)
            if ("TENANT".equalsIgnoreCase(partyType)) {
                agreement.setTenantKycStatus("VERIFIED");
                agreement.setBuyerName(data.getFullName()); // standardising mapping
            } else {
                agreement.setOwnerKycStatus("VERIFIED");
                agreement.setSellerName(data.getFullName());
            }

            // Advance aggregate status
            if ("VERIFIED".equals(agreement.getTenantKycStatus()) && "VERIFIED".equals(agreement.getOwnerKycStatus())) {
                agreement.setAgreementStatus("BOTH_KYC_DONE");
            } else if ("VERIFIED".equals(agreement.getTenantKycStatus())) {
                agreement.setAgreementStatus("TENANT_KYC_DONE");
            } else if ("VERIFIED".equals(agreement.getOwnerKycStatus())) {
                agreement.setAgreementStatus("OWNER_KYC_DONE");
            }

            // Update extracted field values safely directly into the JSON field
            Map<String, Object> values = agreement.getFieldValues();
            if (values == null)
                values = new HashMap<>();

            String prefix = partyType.toLowerCase() + "_";
            if (data.getFullName() != null)
                values.put(prefix + "name", data.getFullName());
            if (data.getAadhaarNumber() != null)
                values.put(prefix + "aadhaar", data.getAadhaarNumber());
            if (data.getPanNumber() != null)
                values.put(prefix + "pan", data.getPanNumber());
            if (data.getAddress() != null)
                values.put(prefix + "address", data.getAddress());

            agreement.setFieldValues(values);
            agreementRepository.save(agreement);

            return data;

        } catch (Exception e) {
            log.error("KYC Processing failed", e);
            throw new RuntimeException("KYC Processing failed", e);
        }
    }

    @Transactional
    public String generateDraft(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));

        AgreementTemplate template = agreement.getTemplate();

        String renderedContent = templateService.renderTemplate(template.getId(), agreement.getFieldValues());

        String pdfPath = uploadBasePath + "/" + agreement.getId() + "/draft_" + System.currentTimeMillis() + ".pdf";
        pdfGenerationService.generateDraftPdf(renderedContent, pdfPath);

        // Storing draft locally for now
        return pdfPath;
    }

    public void requestOtp(Long agreementId, String partyType, String mobile) {
        otpService.generateAndStoreOtp(agreementId, partyType, mobile);
    }

    public String verifyOtp(Long agreementId, String partyType, String otp) {
        boolean valid = otpService.verifyOtp(agreementId, partyType, otp);
        if (!valid) {
            throw new RuntimeException("Invalid or Expired OTP");
        }

        // Generate temporary JWT token for signing context (valid 30 mins)
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.builder()
                .subject(agreementId.toString())
                .claim("partyType", partyType)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(key)
                .compact();
    }

    @Transactional
    public void signAgreement(Long agreementId, String partyType, String ipAddress) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));

        if ("TENANT".equalsIgnoreCase(partyType)) {
            agreement.setTenantSignedAt(LocalDateTime.now());
            agreement.setTenantIpAddress(ipAddress);
            agreement.setAgreementStatus("TENANT_SIGNED");
        } else {
            agreement.setOwnerSignedAt(LocalDateTime.now());
            agreement.setOwnerIpAddress(ipAddress);
            agreement.setAgreementStatus("OWNER_SIGNED");
        }

        // Check if BOTH are signed
        if (agreement.getTenantSignedAt() != null && agreement.getOwnerSignedAt() != null) {
            agreement.setAgreementStatus("COMPLETED");
            agreement.setStatus("COMPLETED");

            generateFinalSealedDigitalDocument(agreement);
        }

        agreementRepository.save(agreement);
    }

    private void generateFinalSealedDigitalDocument(Agreement agreement) {
        log.info("Generating Final Signed PDF for Agreement: {}", agreement.getId());

        String renderedContent = templateService.renderTemplate(agreement.getTemplate().getId(),
                agreement.getFieldValues());

        // Fetch KYC records to pull accurate extracted names & document data for the
        // consent
        KycDocument tenantKyc = kycDocumentRepository.findByAgreementIdAndPartyType(agreement.getId(), "TENANT")
                .stream().findFirst().orElse(new KycDocument());
        KycDocument ownerKyc = kycDocumentRepository.findByAgreementIdAndPartyType(agreement.getId(), "OWNER").stream()
                .findFirst().orElse(new KycDocument());

        String consentBlock = String.format("""
                ═══════════════════════════════════════════════
                        DIGITAL CONSENT CERTIFICATE
                        Generated by sthi Platform
                ═══════════════════════════════════════════════
                This document was digitally consented to by:

                PARTY 1 — TENANT
                  Full Name     : %s
                  Aadhaar No    : %s
                  PAN           : %s
                  Mobile OTP    : Verified ✓
                  Signed At     : %s
                  IP Address    : %s

                PARTY 2 — OWNER
                  Full Name     : %s
                  Aadhaar No    : %s
                  PAN           : %s
                  Mobile OTP    : Verified ✓
                  Signed At     : %s
                  IP Address    : %s

                Note: This document constitutes digital consent under
                the Information Technology Act, 2000 (India).
                For legal disputes, this record serves as evidentiary proof.
                ═══════════════════════════════════════════════
                """,
                tenantKyc.getExtractedName(), tenantKyc.getAadhaarNumber(), tenantKyc.getPanNumber(),
                agreement.getTenantSignedAt(), agreement.getTenantIpAddress(),
                ownerKyc.getExtractedName(), ownerKyc.getAadhaarNumber(), ownerKyc.getPanNumber(),
                agreement.getOwnerSignedAt(), agreement.getOwnerIpAddress());

        String pdfPath = uploadBasePath + "/" + agreement.getId() + "/final_" + System.currentTimeMillis() + ".pdf";
        pdfGenerationService.generateFinalSignedPdf(agreement, renderedContent, pdfPath, consentBlock);

        try {
            byte[] fileBytes = Files.readAllBytes(new File(pdfPath).toPath());
            String hash = HashUtil.generateSha256(fileBytes);
            agreement.setDocHash(hash);
            agreement.setFinalPdfPath(pdfPath);
        } catch (Exception e) {
            log.error("Failed to hash document", e);
        }
    }

    public AgreementStatusResponse getStatus(Long id) {
        Agreement a = agreementRepository.findById(id).orElseThrow();
        return AgreementStatusResponse.builder()
                .agreementId(a.getId())
                .globalStatus(a.getAgreementStatus())
                .tenantKycStatus(a.getTenantKycStatus())
                .ownerKycStatus(a.getOwnerKycStatus())
                .isTenantSigned(a.getTenantSignedAt() != null)
                .isOwnerSigned(a.getOwnerSignedAt() != null)
                .build();
    }
}
