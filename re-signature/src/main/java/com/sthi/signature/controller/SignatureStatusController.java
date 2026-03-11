package com.sthi.signature.controller;

import com.sthi.signature.dto.AgreementStatusResponse;
import com.sthi.signature.entity.Agreement;
import com.sthi.signature.repository.AgreementRepository;
import com.sthi.signature.service.RentalAgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/api/signature/rental")
@RequiredArgsConstructor
public class SignatureStatusController {

    private final RentalAgreementService rentalAgreementService;
    private final AgreementRepository agreementRepository;

    @GetMapping("/{id}/status")
    public ResponseEntity<AgreementStatusResponse> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(rentalAgreementService.getStatus(id));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFinalPdf(@PathVariable Long id) {
        Agreement agreement = agreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));

        if (!"COMPLETED".equalsIgnoreCase(agreement.getAgreementStatus())) {
            throw new RuntimeException("Agreement is not finalized yet");
        }

        File file = new File(agreement.getFinalPdfPath());
        if (!file.exists()) {
            throw new RuntimeException("PDF file not found on disk");
        }

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"agreement_" + id + "_final.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
