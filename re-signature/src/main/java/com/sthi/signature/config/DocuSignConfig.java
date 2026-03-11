package com.sthi.signature.config;

import com.docusign.esign.client.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
public class DocuSignConfig {

    @Value("${docusign.base-url}")
    private String baseUrl;

    @Value("${docusign.account-id}")
    private String accountId;

    @Value("${docusign.integration-key}")
    private String integrationKey;

    @Value("${docusign.user-id}")
    private String userId;

    @Value("${docusign.private-key-path}")
    private Resource privateKeyResource;

    @Bean
    public ApiClient docuSignApiClient() throws Exception {
        ApiClient apiClient = new ApiClient(baseUrl);
        byte[] privateKeyBytes = StreamUtils.copyToByteArray(privateKeyResource.getInputStream());

        List<String> scopes = Collections.singletonList("signature");

        // This is a typical OAuth JWT grant setup
        apiClient.setOAuthBasePath("account-d.docusign.com");
        com.docusign.esign.client.auth.OAuth.OAuthToken token = apiClient.requestJWTUserToken(
                integrationKey,
                userId,
                scopes,
                privateKeyBytes,
                3600);

        apiClient.setAccessToken(token.getAccessToken(), token.getExpiresIn());
        return apiClient;
    }
}
