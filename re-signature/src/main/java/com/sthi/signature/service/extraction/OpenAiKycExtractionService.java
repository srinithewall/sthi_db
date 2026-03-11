package com.sthi.signature.service.extraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sthi.signature.dto.KycExtractedData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("openai")
@RequiredArgsConstructor
public class OpenAiKycExtractionService implements KycExtractionService {

    @Value("${ai.api.key:}")
    private String openAiKey;

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String AADHAAR_PROMPT = "Extract the following fields from this Aadhaar card image and return as JSON only, no explanation:\n{\n  \"fullName\": \"\",\n  \"dateOfBirth\": \"DD/MM/YYYY\",\n  \"gender\": \"\",\n  \"aadhaarNumber\": \"XXXX XXXX XXXX\",\n  \"address\": \"\",\n  \"pincode\": \"\"\n}\nIf a field is not visible or unclear, set value to null.";
    private static final String PAN_PROMPT = "Extract the following fields from this PAN card image and return as JSON only, no explanation:\n{\n  \"fullName\": \"\",\n  \"fatherName\": \"\",\n  \"dateOfBirth\": \"DD/MM/YYYY\",\n  \"panNumber\": \"\"\n}\nIf a field is not visible or unclear, set value to null.";

    @Override
    public KycExtractedData extractFromImage(MultipartFile file, String docType) {
        log.info("Extracting data from {} via OpenAI Vision", docType);

        try {
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            String mimeType = file.getContentType() != null ? file.getContentType() : "image/jpeg";
            String imageUrl = "data:" + mimeType + ";base64," + base64Image;

            String prompt = docType.equalsIgnoreCase("AADHAAR") ? AADHAAR_PROMPT : PAN_PROMPT;

            Map<String, Object> messageContent = new HashMap<>();
            messageContent.put("type", "image_url");
            messageContent.put("image_url", Map.of("url", imageUrl));

            Map<String, Object> textContent = new HashMap<>();
            textContent.put("type", "text");
            textContent.put("text", prompt);

            Map<String, Object> message = new HashMap<>();
            message.put("role", "user");
            message.put("content", List.of(textContent, messageContent));

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-4-vision-preview");
            requestBody.put("messages", List.of(message));
            requestBody.put("max_tokens", 500);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAiKey);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://api.openai.com/v1/chat/completions", entity, Map.class);

            // For this POC, directly constructing object without deep JSON parsing
            // exception handling
            Map<String, Object> body = response.getBody();
            List<Map<String, Object>> choices = (List<Map<String, Object>>) body.get("choices");
            Map<String, Object> messageMap = (Map<String, Object>) choices.get(0).get("message");
            String jsonContent = (String) messageMap.get("content");

            // Strip markdown JSON blocks if present
            if (jsonContent.startsWith("```json")) {
                jsonContent = jsonContent.substring(7);
            }
            if (jsonContent.endsWith("```")) {
                jsonContent = jsonContent.substring(0, jsonContent.length() - 3);
            }

            KycExtractedData data = objectMapper.readValue(jsonContent.trim(), KycExtractedData.class);
            data.setDocType(docType.toUpperCase());
            data.setRawAiResponse(jsonContent);
            data.setConfidenceScore(0.95); // Static for POC

            // PAN validation
            if ("PAN".equalsIgnoreCase(docType)) {
                String pan = data.getPanNumber();
                if (pan != null && !pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
                    throw new RuntimeException("Invalid PAN format detected: " + pan);
                }
            }

            return data;

        } catch (Exception e) {
            log.error("Failed to extract KYC data via OpenAI", e);
            throw new RuntimeException("KYC Extraction Failed", e);
        }
    }
}
