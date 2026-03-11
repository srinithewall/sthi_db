package com.sthi.signature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigningRequest {
    private String partyType; // TENANT, OWNER
    private String mobile; // Used for request OTP
    private String otp; // Used for verify OTP
}
