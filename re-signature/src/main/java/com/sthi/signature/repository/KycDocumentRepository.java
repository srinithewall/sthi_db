package com.sthi.signature.repository;

import com.sthi.signature.entity.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KycDocumentRepository extends JpaRepository<KycDocument, Long> {
    List<KycDocument> findByAgreementIdAndPartyType(Long agreementId, String partyType);
}
