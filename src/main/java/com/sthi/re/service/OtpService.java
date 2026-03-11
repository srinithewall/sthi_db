package com.sthi.re.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sthi.re.model.ReOtpVerification;
import com.sthi.re.repo.ReOtpVerificationRepository;

@Service
public class OtpService {

    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_MINUTES = 5;
    private static final int MAX_ATTEMPTS = 3;

    private static final Integer NOT_VERIFIED = 0;
    private static final Integer VERIFIED = 1;

    private static final String OTP_SMS_TEMPLATE =
            "Your OTP for callback request is %s. "
          + "It is valid for %d minutes.";

    private final ReOtpVerificationRepository otpRepo;
    private final SmsService smsService;

    public OtpService(
            ReOtpVerificationRepository otpRepo,
            @Qualifier("msg91SmsService") SmsService smsService
    ) {
        this.otpRepo = otpRepo;
        this.smsService = smsService;
    }

    /**
     * Generate & send OTP
     */
    @Transactional
    public void generateAndSendOtp(String phone) {

        // Optional: throttle OTP requests (basic)
        otpRepo.findTopByPhoneOrderByCreatedAtDesc(phone)
               .ifPresent(existing -> {
                   if (existing.getAttempts() >= MAX_ATTEMPTS &&
                       existing.getExpiresAt().isAfter(LocalDateTime.now())) {

                       throw new IllegalStateException(
                           "Too many OTP attempts. Please try again later."
                       );
                   }
               });

        String otp = generateOtp();

        ReOtpVerification record = new ReOtpVerification();
        record.setPhone(phone);
        record.setOtp(otp);
        record.setIsVerified(NOT_VERIFIED);
        record.setAttempts(0);
        record.setExpiresAt(
                LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES)
        );

        otpRepo.save(record);

        // Send OTP via SMS
        String message = String.format(
                OTP_SMS_TEMPLATE,
                otp,
                OTP_EXPIRY_MINUTES
        );

        // 📝 LOG OTP TO CONSOLE FOR TESTING
        System.out.println("--------------------------------------------------");
        System.out.println("🔑 NEW OTP GENERATED");
        System.out.println("To      : " + phone);
        System.out.println("OTP     : " + otp);
        System.out.println("Message : " + message);
        System.out.println("--------------------------------------------------");

        smsService.sendSms(phone, message);
    }

    /**
     * Verify OTP
     */
    @Transactional
    public boolean verifyOtp(String phone, String otpInput) {

        ReOtpVerification record = otpRepo
            .findTopByPhoneAndIsVerifiedAndExpiresAtAfterOrderByCreatedAtDesc(
                phone,
                NOT_VERIFIED,
                LocalDateTime.now()
            )
            .orElseThrow(() ->
                new IllegalStateException("OTP expired or not found")
            );

        if (record.getAttempts() >= MAX_ATTEMPTS) {
            throw new IllegalStateException(
                "Maximum OTP attempts exceeded"
            );
        }

        if (!record.getOtp().equals(otpInput)) {
            record.setAttempts(record.getAttempts() + 1);
            otpRepo.save(record);

            throw new IllegalStateException("Invalid OTP");
        }

        // Success
        record.setIsVerified(VERIFIED);
        otpRepo.save(record);

        return true;
    }

    // ---------- Helpers ----------

    private String generateOtp() {
        Random random = new Random();
        int min = (int) Math.pow(10, OTP_LENGTH - 1);
        int max = (int) Math.pow(10, OTP_LENGTH) - 1;
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}
