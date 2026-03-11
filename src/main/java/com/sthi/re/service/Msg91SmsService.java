package com.sthi.re.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("msg91SmsService")
public class Msg91SmsService implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(Msg91SmsService.class);

    @Value("${msg91.api.key:}")
    private String apiKey;

    @Value("${msg91.template.id:}")
    private String templateId;

    private static final String OTP_URL = "https://control.msg91.com/api/v5/otp";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendSms(String phoneNumber, String message) {

        // Safety check
        if (apiKey == null || apiKey.isBlank()) {
            logger.warn("MSG91 API key not configured. SMS skipped for: {}", phoneNumber);
            return;
        }

        if (templateId == null || templateId.isBlank()) {
            logger.error("MSG91 Template ID is missing! Check application.properties");
            return;
        }

        try {
            // Extract the 6-digit OTP from the message
            String otp = "";
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\d{6}").matcher(message);
            if (m.find()) {
                otp = m.group();
            }

            // Build JSON body
            String jsonBody = String.format(
                "{\"template_id\":\"%s\",\"mobile\":\"91%s\",\"otp\":\"%s\"}",
                templateId, phoneNumber, otp
            );

            // Set headers with authkey
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authkey", apiKey);

            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            logger.info("Triggering MSG91 OTP API (POST) for: {} | OTP: {}", phoneNumber, otp);

            ResponseEntity<String> response = restTemplate.exchange(
                OTP_URL, HttpMethod.POST, request, String.class
            );

            logger.info("MSG91 Response for {}: Status={} Body={}",
                phoneNumber, response.getStatusCode(), response.getBody());

        } catch (Exception ex) {
            logger.error("Failed to send SMS to {}: {}", phoneNumber, ex.getMessage(), ex);
        }
    }
}
