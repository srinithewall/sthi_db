package com.sthi.signature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementRequest {
    private Long templateId;
    private String brokerName;
    private String brokerEmail;
    private String buyerName;
    private String buyerEmail;
    private String sellerName;
    private String sellerEmail;
    private Map<String, Object> fieldValues;
}
