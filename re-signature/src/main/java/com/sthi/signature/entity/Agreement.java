package com.sthi.signature.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "ds_agreements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private AgreementTemplate template;

    @Column(name = "envelope_id", length = 100)
    private String envelopeId;

    @Column(name = "broker_name")
    private String brokerName;

    @Column(name = "broker_email")
    private String brokerEmail;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_email")
    private String sellerEmail;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "field_values", columnDefinition = "JSON")
    private Map<String, Object> fieldValues;

    @Column(name = "tenant_kyc_status")
    @Builder.Default
    private String tenantKycStatus = "PENDING"; // PENDING, UPLOADED, VERIFIED

    @Column(name = "owner_kyc_status")
    @Builder.Default
    private String ownerKycStatus = "PENDING";

    @Column(name = "tenant_signed_at")
    private LocalDateTime tenantSignedAt;

    @Column(name = "owner_signed_at")
    private LocalDateTime ownerSignedAt;

    @Column(name = "tenant_ip_address", length = 45)
    private String tenantIpAddress;

    @Column(name = "owner_ip_address", length = 45)
    private String ownerIpAddress;

    @Column(name = "final_pdf_path", length = 500)
    private String finalPdfPath;

    @Column(name = "doc_hash", length = 64)
    private String docHash;

    @Column(length = 20)
    @Builder.Default
    private String status = "DRAFT"; // ENUM in DB: DRAFT, SENT, PARTIAL, COMPLETED, VOIDED

    @Column(name = "agreement_status", length = 20)
    @Builder.Default
    private String agreementStatus = "DRAFT"; // DRAFT, TENANT_KYC_DONE, OWNER_KYC_DONE, BOTH_KYC_DONE, TENANT_SIGNED,
                                              // OWNER_SIGNED, COMPLETED

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
