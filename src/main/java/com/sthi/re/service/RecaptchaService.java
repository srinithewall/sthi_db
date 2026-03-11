package com.sthi.re.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {

    @Value("${recaptcha.secret:}")
    private String secret;

    @Value("${app.recaptcha.enabled:true}")
    private boolean recaptchaEnabled;

    private static final String VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    public void validateToken(String token) {

        // ✅ Skip validation if disabled
        if (!recaptchaEnabled) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> response =
                restTemplate.postForObject(
                        VERIFY_URL + "?secret=" + secret + "&response=" + token,
                        null,
                        Map.class
                );

        if (response == null) {
            throw new IllegalStateException("reCAPTCHA verification failed");
        }

        Object successObj = response.get("success");
        boolean success = Boolean.parseBoolean(String.valueOf(successObj));

        if (!success) {
            throw new IllegalStateException("Invalid reCAPTCHA");
        }

        Object scoreObj = response.get("score");
        double score = scoreObj != null
                ? Double.parseDouble(String.valueOf(scoreObj))
                : 0.0;

        if (score < 0.5) {
            throw new IllegalStateException("Low reCAPTCHA score");
        }
    }
}