package com.sthi.signature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KycExtractedData {
    private String fullName;
    private String dateOfBirth;
    private String address;
    private String aadhaarNumber; // full number - store as-is for POC
    private String panNumber;
    private String fatherName; // from PAN card
    private String gender; // from Aadhaar
    private String pincode; // from Aadhaar address
    private String docType; // AADHAAR or PAN
    private Double confidenceScore; // 0.0 to 1.0 from AI response
    private String rawAiResponse; // store for debugging
}
