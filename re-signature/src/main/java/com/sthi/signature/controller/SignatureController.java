package com.sthi.signature.controller;

import com.sthi.signature.dto.AgreementRequest;
import com.sthi.signature.dto.AgreementResponse;
import com.sthi.signature.dto.TemplateDto;
import com.sthi.signature.service.SignatureFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/signature")
@RequiredArgsConstructor
public class SignatureController {

    private final SignatureFacade signatureFacade;

    @PostMapping("/agreements")
    public ResponseEntity<AgreementResponse> initiateAgreement(@RequestBody AgreementRequest request) {
        log.info("Initiating agreement for template: {}", request.getTemplateId());
        AgreementResponse response = signatureFacade.initiateAgreement(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/agreements/{id}")
    public ResponseEntity<String> getAgreementStatus(@PathVariable Long id) {
        return ResponseEntity.ok(signatureFacade.getStatus(id));
    }

    @GetMapping("/agreements/{id}/document")
    public ResponseEntity<byte[]> getSignedDocument(@PathVariable Long id) {
        byte[] doc = signatureFacade.getSignedDocument(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "SignedAgreement.pdf");

        return new ResponseEntity<>(doc, headers, HttpStatus.OK);
    }

    @GetMapping("/templates")
    public ResponseEntity<List<TemplateDto>> getAvailableTemplates() {
        return ResponseEntity.ok(signatureFacade.getAvailableTemplates());
    }
}
