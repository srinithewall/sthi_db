package com.sthi.signature.controller;

import com.sthi.signature.entity.AgreementTemplate;
import com.sthi.signature.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/signature/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    public ResponseEntity<List<AgreementTemplate>> listTemplates() {
        return ResponseEntity.ok(templateService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementTemplate> getTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(templateService.getTemplateById(id));
    }
}
