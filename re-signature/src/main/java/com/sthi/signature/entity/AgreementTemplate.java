package com.sthi.signature.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ds_agreement_templates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 20)
    private String version;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "template_type", length = 50)
    private String templateType;

    @Column(name = "docusign_template_id", length = 100)
    private String docusignTemplateId;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
