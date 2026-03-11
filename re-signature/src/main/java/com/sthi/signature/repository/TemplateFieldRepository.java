package com.sthi.signature.repository;

import com.sthi.signature.entity.TemplateField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateFieldRepository extends JpaRepository<TemplateField, Long> {
    List<TemplateField> findByTemplateIdOrderByDisplayOrderAsc(Long templateId);
}
