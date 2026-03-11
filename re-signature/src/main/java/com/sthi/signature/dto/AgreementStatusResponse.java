package com.sthi.signature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementStatusResponse {
    private Long agreementId;
    private String globalStatus; // e.g. DRAFT, TENANT_KYC_DONE, COMPLETED
    private String tenantKycStatus;
    private String ownerKycStatus;
    private boolean isTenantSigned;
    private boolean isOwnerSigned;
}
