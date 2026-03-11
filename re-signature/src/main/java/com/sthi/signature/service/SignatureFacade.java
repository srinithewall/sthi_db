package com.sthi.signature.service;

import com.sthi.signature.dto.AgreementRequest;
import com.sthi.signature.dto.AgreementResponse;
import com.sthi.signature.dto.TemplateDto;
import com.sthi.signature.entity.Agreement;
import com.sthi.signature.repository.AgreementTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignatureFacade {

    private final AgreementService agreementService;
    private final TemplateService templateService;
    private final DocuSignService docuSignService;
    private final AgreementTemplateRepository templateRepository;

    @Transactional
    public AgreementResponse initiateAgreement(AgreementRequest request) {
        // 1. Create DB Draft
        Agreement draft = agreementService.createAgreement(request);
        draft.setTemplate(templateService.getTemplateById(request.getTemplateId()));

        // 2. Render content
        String content = templateService.renderTemplate(request.getTemplateId(), request.getFieldValues());

        // 3. Send to DocuSign
        String envelopeId = docuSignService.createAndSendEnvelope(draft, content);

        // 4. Update draft with Envelope ID and send status
        Agreement sent = agreementService.updateEnvelopeId(draft.getId(), envelopeId);

        return AgreementResponse.builder()
                .id(sent.getId())
                .templateId(sent.getTemplate().getId())
                .envelopeId(sent.getEnvelopeId())
                .status(sent.getStatus())
                .createdAt(sent.getCreatedAt())
                .sentAt(sent.getSentAt())
                .build();
    }

    public String getStatus(Long agreementId) {
        return agreementService.getAgreement(agreementId).getStatus();
    }

    public byte[] getSignedDocument(Long agreementId) {
        Agreement agreement = agreementService.getAgreement(agreementId);
        if (agreement.getEnvelopeId() == null) {
            throw new RuntimeException("No envelope ID for agreement");
        }
        return docuSignService.downloadSignedDocument(agreement.getEnvelopeId());
    }

    public List<TemplateDto> getAvailableTemplates() {
        return templateRepository.findByIsActiveTrue().stream()
                .map(t -> TemplateDto.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .version(t.getVersion())
                        .build())
                .collect(Collectors.toList());
    }
}
