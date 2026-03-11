package com.sthi.signature.repository;

import com.sthi.signature.entity.SignatureOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignatureOtpRepository extends JpaRepository<SignatureOtp, Long> {
    Optional<SignatureOtp> findTopByAgreementIdAndPartyTypeOrderByCreatedAtDesc(Long agreementId, String partyType);
}
