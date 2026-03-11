package com.sthi.signature.service;

import com.sthi.signature.entity.AgreementTemplate;
import com.sthi.signature.repository.AgreementTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateService {

    private final AgreementTemplateRepository templateRepository;

    public AgreementTemplate getTemplateById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found: " + id));
    }

    public java.util.List<AgreementTemplate> findAllActive() {
        return templateRepository.findByIsActiveTrue();
    }

    public String renderTemplate(Long templateId, Map<String, Object> values) {
        AgreementTemplate template = getTemplateById(templateId);
        String content = template.getContent();

        if (values != null) {
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                String token = "{{" + entry.getKey() + "}}";
                String valueStr = entry.getValue() != null ? entry.getValue().toString() : "";
                content = content.replace(token, valueStr);
            }
        }
        return content;
    }
}
