package com.sthi.signature.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ds_template_fields")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private AgreementTemplate template;

    @Column(name = "field_key", length = 100, nullable = false)
    private String fieldKey;

    @Column(name = "field_label", nullable = false)
    private String fieldLabel;

    @Column(name = "field_type", nullable = false)
    private String fieldType; // ENUM in DB

    @Column(name = "is_required", nullable = false)
    @Builder.Default
    private Boolean isRequired = true;

    @Column(name = "display_order")
    @Builder.Default
    private Integer displayOrder = 0;
}
