package com.sthi.re.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sthi.re.dto.response.OtpResponse;
import com.sthi.re.dto.request.RequestOtpRequest;
import com.sthi.re.dto.request.VerifyOtpRequest;
import com.sthi.re.service.OtpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/otp")
@Validated
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    /**
     * Step 1: Request OTP
     */
    @PostMapping("/request")
    public ResponseEntity<OtpResponse> requestOtp(
            @Valid @RequestBody RequestOtpRequest request
    ) {

        otpService.generateAndSendOtp(request.getPhone());

        return ResponseEntity.ok(
            new OtpResponse(
                "SUCCESS",
                "OTP sent successfully"
            )
        );
    }

    /**
     * Step 2: Verify OTP
     */
    @PostMapping("/verify")
    public ResponseEntity<OtpResponse> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request
    ) {

        otpService.verifyOtp(
                request.getPhone(),
                request.getOtp()
        );

        return ResponseEntity.ok(
            new OtpResponse(
                "SUCCESS",
                "OTP verified successfully"
            )
        );
    }
}
