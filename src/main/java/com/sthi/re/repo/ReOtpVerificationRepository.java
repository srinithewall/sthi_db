package com.sthi.re.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sthi.re.model.ReOtpVerification;

public interface ReOtpVerificationRepository
        extends JpaRepository<ReOtpVerification, Long> {

    /**
     * Fetch latest OTP for a phone number
     */
    Optional<ReOtpVerification> findTopByPhoneOrderByCreatedAtDesc(String phone);

    /**
     * Fetch valid (not expired, not verified) OTP
     */
    Optional<ReOtpVerification> findTopByPhoneAndIsVerifiedAndExpiresAtAfterOrderByCreatedAtDesc(
            String phone,
            Integer isVerified,
            LocalDateTime now
    );

    /**
     * Optional cleanup helper
     */
    void deleteByExpiresAtBefore(LocalDateTime time);
}
