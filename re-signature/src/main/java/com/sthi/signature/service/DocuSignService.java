package com.sthi.signature.service;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.model.*;
import com.sthi.signature.entity.Agreement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocuSignService {

    private final ApiClient apiClient;

    @Value("${docusign.account-id}")
    private String accountId;

    public String createAndSendEnvelope(Agreement agreement, String documentContent) {
        try {
            EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
            EnvelopeDefinition env = makeEnvelope(agreement, documentContent);
            EnvelopeSummary results = envelopesApi.createEnvelope(accountId, env);
            return results.getEnvelopeId();
        } catch (Exception e) {
            log.error("Error creating DocuSign envelope", e);
            throw new RuntimeException("DocuSign integration error", e);
        }
    }

    public byte[] downloadSignedDocument(String envelopeId) {
        try {
            EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
            return envelopesApi.getDocument(accountId, envelopeId, "combined");
        } catch (Exception e) {
            log.error("Error downloading DocuSign envelope document", e);
            throw new RuntimeException("DocuSign integration error", e);
        }
    }

    private EnvelopeDefinition makeEnvelope(Agreement agreement, String documentContent) {
        Document document = new Document();
        document.setDocumentBase64(Base64.getEncoder().encodeToString(documentContent.getBytes()));
        document.setName("Agreement");
        document.setFileExtension("txt");
        document.setDocumentId("1");

        // Buyer Signer
        Signer buyerSigner = new Signer();
        buyerSigner.setEmail(agreement.getBuyerEmail());
        buyerSigner.setName(agreement.getBuyerName());
        buyerSigner.setRecipientId("1");
        buyerSigner.setRoutingOrder("1");

        SignHere buyerSignHere = new SignHere();
        buyerSignHere.setAnchorString("\\s1\\");
        buyerSignHere.setAnchorUnits("pixels");
        buyerSignHere.setAnchorYOffset("10");
        buyerSignHere.setAnchorXOffset("20");

        DateSigned buyerDateSigned = new DateSigned();
        buyerDateSigned.setAnchorString("\\d1\\");
        buyerDateSigned.setAnchorUnits("pixels");
        buyerDateSigned.setAnchorYOffset("10");
        buyerDateSigned.setAnchorXOffset("20");

        Tabs buyerTabs = new Tabs();
        buyerTabs.setSignHereTabs(Arrays.asList(buyerSignHere));
        buyerTabs.setDateSignedTabs(Arrays.asList(buyerDateSigned));
        buyerSigner.setTabs(buyerTabs);

        // Seller Signer
        Signer sellerSigner = new Signer();
        sellerSigner.setEmail(agreement.getSellerEmail());
        sellerSigner.setName(agreement.getSellerName());
        sellerSigner.setRecipientId("2");
        sellerSigner.setRoutingOrder("2");

        SignHere sellerSignHere = new SignHere();
        sellerSignHere.setAnchorString("\\s2\\");
        sellerSignHere.setAnchorUnits("pixels");
        sellerSignHere.setAnchorYOffset("10");
        sellerSignHere.setAnchorXOffset("20");

        DateSigned sellerDateSigned = new DateSigned();
        sellerDateSigned.setAnchorString("\\d2\\");
        sellerDateSigned.setAnchorUnits("pixels");
        sellerDateSigned.setAnchorYOffset("10");
        sellerDateSigned.setAnchorXOffset("20");

        Tabs sellerTabs = new Tabs();
        sellerTabs.setSignHereTabs(Arrays.asList(sellerSignHere));
        sellerTabs.setDateSignedTabs(Arrays.asList(sellerDateSigned));
        sellerSigner.setTabs(sellerTabs);

        Recipients recipients = new Recipients();
        recipients.setSigners(Arrays.asList(buyerSigner, sellerSigner));

        EnvelopeDefinition env = new EnvelopeDefinition();
        env.setEmailSubject("Please sign your Real Estate Agreement");
        env.setDocuments(Arrays.asList(document));
        env.setRecipients(recipients);
        env.setStatus("sent");

        return env;
    }
}
