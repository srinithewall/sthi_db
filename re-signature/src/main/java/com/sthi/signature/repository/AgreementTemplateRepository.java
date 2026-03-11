package com.sthi.signature.repository;

import com.sthi.signature.entity.AgreementTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementTemplateRepository extends JpaRepository<AgreementTemplate, Long> {
    List<AgreementTemplate> findByIsActiveTrue();
}
