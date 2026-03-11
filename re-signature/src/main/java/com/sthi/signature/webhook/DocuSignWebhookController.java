package com.sthi.signature.webhook;

import com.sthi.signature.entity.Agreement;
import com.sthi.signature.entity.AgreementSignature;
import com.sthi.signature.repository.AgreementRepository;
import com.sthi.signature.repository.AgreementSignatureRepository;
import com.sthi.signature.service.DocuSignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.internet.MimeMessage;
import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/signature/webhook")
@RequiredArgsConstructor
public class DocuSignWebhookController {

    private final AgreementRepository agreementRepository;
    private final AgreementSignatureRepository signatureRepository;
    private final DocuSignService docuSignService;
    private final JavaMailSender mailSender;

    @PostMapping("/docusign")
    @Transactional
    public ResponseEntity<String> handleDocuSignWebhook(@RequestBody String payload) {
        log.info("Received DocuSign webhook payload");

        // Very basic parsing for demo. In production, securely parse XML or JSON event
        // message
        if (payload.contains("envelope-completed") || payload.contains("Completed")) {
            // Extract envelope ID from payload (simplified logic)
            String envelopeId = extractEnvelopeId(payload);

            if (envelopeId != null) {
                agreementRepository.findByEnvelopeId(envelopeId).ifPresent(agreement -> {
                    agreement.setStatus("COMPLETED");
                    agreement.setCompletedAt(LocalDateTime.now());
                    agreementRepository.save(agreement);

                    // Add dummy signatures
                    AgreementSignature buyerSig = AgreementSignature.builder()
                            .agreement(agreement)
                            .signerRole("BUYER")
                            .signerName(agreement.getBuyerName())
                            .signerEmail(agreement.getBuyerEmail())
                            .signedAt(LocalDateTime.now())
                            .build();

                    AgreementSignature sellerSig = AgreementSignature.builder()
                            .agreement(agreement)
                            .signerRole("SELLER")
                            .signerName(agreement.getSellerName())
                            .signerEmail(agreement.getSellerEmail())
                            .signedAt(LocalDateTime.now())
                            .build();

                    signatureRepository.save(buyerSig);
                    signatureRepository.save(sellerSig);

                    // Download and Email the PDF mapping
                    try {
                        byte[] pdfData = docuSignService.downloadSignedDocument(envelopeId);
                        sendEmailWithAttachment(agreement.getBrokerEmail(), "Agreement Completed",
                                "The agreement has been completely signed.", pdfData);
                    } catch (Exception e) {
                        log.error("Failed to fetch or send document", e);
                    }
                });
            }
        } else if (payload.contains("envelope-declined") || payload.contains("Declined")) {
            String envelopeId = extractEnvelopeId(payload);
            if (envelopeId != null) {
                agreementRepository.findByEnvelopeId(envelopeId).ifPresent(agreement -> {
                    agreement.setStatus("VOIDED");
                    agreementRepository.save(agreement);
                });
            }
        }

        return ResponseEntity.ok("Webhook processed");
    }

    private String extractEnvelopeId(String payload) {
        // Simple regex or string parsing to find <envelopeId> value </envelopeId>
        // Example for XML payload wrapper
        try {
            if (payload.contains("<envelopeId>")) {
                int start = payload.indexOf("<envelopeId>") + 12;
                int end = payload.indexOf("</envelopeId>");
                return payload.substring(start, end);
            }
        } catch (Exception e) {
            log.warn("Could not extract envelope ID from payload");
        }
        return null; // fallback
    }

    private void sendEmailWithAttachment(String to, String subject, String text, byte[] attachmentData) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            DataSource dataSource = new ByteArrayDataSource(attachmentData, "application/pdf");
            helper.addAttachment("Signed_Agreement.pdf", dataSource);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Error sending email", e);
        }
    }
}
