package com.sthi.signature.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ds_agreement_signatures")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreement;

    @Column(name = "signer_role", nullable = false)
    private String signerRole; // BUYER, SELLER

    @Column(name = "signer_name")
    private String signerName;

    @Column(name = "signer_email")
    private String signerEmail;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "docusign_recipient_id", length = 50)
    private String docusignRecipientId;
}
