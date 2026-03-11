package com.sthi.signature.service;

import com.sthi.signature.entity.Agreement;
import com.sthi.signature.entity.SignatureOtp;
import com.sthi.signature.repository.AgreementRepository;
import com.sthi.signature.repository.SignatureOtpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpService {

    private final SignatureOtpRepository otpRepository;
    private final AgreementRepository agreementRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${otp.expiry.minutes:10}")
    private int expiryMinutes;

    @Value("${otp.sms.provider:console}")
    private String smsProvider;

    public void generateAndStoreOtp(Long agreementId, String partyType, String mobileNumber) {
        log.info("Generating OTP for agreement: {}, party: {}", agreementId, partyType);

        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new RuntimeException("Agreement not found"));

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        if ("console".equalsIgnoreCase(smsProvider)) {
            log.warn("-------- MOCK SMS OUTBOX --------");
            log.warn("To: {}", mobileNumber);
            log.warn("Message: Your OTP for signing is {}. Valid for {} mins.", otp, expiryMinutes);
            log.warn("---------------------------------");
        } else {
            // Future: integrate fast2sms or others here
            log.info("Would send SMS via provider: {}", smsProvider);
        }

        String hashedOtp = passwordEncoder.encode(otp);

        SignatureOtp signatureOtp = SignatureOtp.builder()
                .agreement(agreement)
                .partyType(partyType)
                .mobileNumber(mobileNumber)
                .otpHash(hashedOtp)
                .expiresAt(LocalDateTime.now().plusMinutes(expiryMinutes))
                .build();

        otpRepository.save(signatureOtp);
    }

    public boolean verifyOtp(Long agreementId, String partyType, String enteredOtp) {
        Optional<SignatureOtp> optRecord = otpRepository
                .findTopByAgreementIdAndPartyTypeOrderByCreatedAtDesc(agreementId, partyType);

        if (optRecord.isEmpty()) {
            return false;
        }

        SignatureOtp record = optRecord.get();

        if (record.getIsUsed()) {
            log.warn("OTP already used");
            return false;
        }

        if (LocalDateTime.now().isAfter(record.getExpiresAt())) {
            log.warn("OTP has expired");
            return false;
        }

        if (passwordEncoder.matches(enteredOtp, record.getOtpHash())) {
            record.setIsUsed(true);
            record.setVerifiedAt(LocalDateTime.now());
            otpRepository.save(record);
            return true;
        }

        return false;
    }
}
