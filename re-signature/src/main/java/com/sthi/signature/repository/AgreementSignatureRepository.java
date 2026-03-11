package com.sthi.signature.repository;

import com.sthi.signature.entity.AgreementSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementSignatureRepository extends JpaRepository<AgreementSignature, Long> {
    List<AgreementSignature> findByAgreementId(Long agreementId);
}
