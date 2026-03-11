package com.sthi.signature.service;

import com.sthi.signature.dto.AgreementRequest;
import com.sthi.signature.entity.Agreement;
import com.sthi.signature.entity.AgreementTemplate;
import com.sthi.signature.repository.AgreementRepository;
import com.sthi.signature.repository.AgreementTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementTemplateRepository templateRepository;

    @Transactional
    public Agreement createAgreement(AgreementRequest request) {
        AgreementTemplate template = null;
        if (request.getTemplateId() != null) {
            template = templateRepository.findById(request.getTemplateId())
                    .orElseThrow(() -> new RuntimeException("Template not found: " + request.getTemplateId()));
        }

        Agreement agreement = Agreement.builder()
                .template(template)
                .brokerName(request.getBrokerName())
                .brokerEmail(request.getBrokerEmail())
                .buyerName(request.getBuyerName())
                .buyerEmail(request.getBuyerEmail())
                .sellerName(request.getSellerName())
                .sellerEmail(request.getSellerEmail())
                .fieldValues(request.getFieldValues())
                .status("DRAFT")
                .agreementStatus("DRAFT")
                .build();
        return agreementRepository.save(agreement);
    }

    @Transactional
    public Agreement updateEnvelopeId(Long id, String envelopeId) {
        Agreement agreement = agreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));
        agreement.setEnvelopeId(envelopeId);
        agreement.setStatus("SENT");
        agreement.setSentAt(LocalDateTime.now());
        return agreementRepository.save(agreement);
    }

    public java.util.List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }

    public Agreement getAgreement(Long id) {
        return agreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));
    }
}
