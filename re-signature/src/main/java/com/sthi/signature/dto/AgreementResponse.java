package com.sthi.signature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementResponse {
    private Long id;
    private Long templateId;
    private String envelopeId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
    private LocalDateTime completedAt;
}
