package com.sthi.signature.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ds_kyc_documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KycDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreement;

    @Column(name = "party_type", nullable = false)
    private String partyType; // TENANT, OWNER

    @Column(name = "doc_type", nullable = false)
    private String docType; // AADHAAR, PAN

    @Column(name = "file_path", length = 500, nullable = false)
    private String filePath;

    @Column(name = "extracted_name")
    private String extractedName;

    @Column(name = "extracted_dob", length = 20)
    private String extractedDob;

    @Column(name = "extracted_gender", length = 10)
    private String extractedGender;

    @Column(name = "extracted_address", columnDefinition = "TEXT")
    private String extractedAddress;

    @Column(name = "extracted_pincode", length = 10)
    private String extractedPincode;

    // TODO: Mask to XXXX-XXXX-1234 before production release
    // Pending: DPDP Act 2023 compliance review
    @Column(name = "aadhaar_number", length = 20)
    private String aadhaarNumber;

    @Column(name = "pan_number", length = 10)
    private String panNumber;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "confidence_score", precision = 3, scale = 2)
    private Double confidenceScore;

    @Column(name = "raw_ai_response", columnDefinition = "TEXT")
    private String rawAiResponse;

    @Column(name = "extraction_status")
    @Builder.Default
    private String extractionStatus = "PENDING"; // PENDING, SUCCESS, FAILED

    @Column(name = "extracted_at")
    private LocalDateTime extractedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
