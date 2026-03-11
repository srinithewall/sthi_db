package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_leads")
public class ReLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // ---------- Customer Info ----------

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "message", length = 1000)
    private String message;
    
    @Column(name = "requested_by_user_id")
    private Integer requestedByUserId; // NULL for guest users


    // ---------- Lead Context ----------

    @Column(name = "project_id")
    private Long projectId;
    
    
    private Long propertyId;
    /**
     * FK to users.id (advisor)
     */
    @Column(name = "advisor_id")
    private Integer advisorId;

    /**
     * NEW
     * CONTACTED
     * FOLLOW_UP
     * VISIT_SCHEDULED
     * CLOSED
     * LOST
     */
    @Column(name = "status")
    private String status;

    /**
     * WEB
     * WHATSAPP
     * FACEBOOK
     */
    @Column(name = "source")
    private String source;

    // ---------- Audit ----------

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "advisor_whatsapp_link", length = 1000)
    private String advisorWhatsappLink;

   
    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Integer advisorId) {
        this.advisorId = advisorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRequestedByUserId() {
        return requestedByUserId;
    }

    public void setRequestedByUserId(Integer requestedByUserId) {
        this.requestedByUserId = requestedByUserId;
    }

    // ---------- Helper Methods (Optional) ----------

    @Transient
    public boolean isNewLead() {
        return "NEW".equalsIgnoreCase(this.status);
    }
    
    public String getAdvisorWhatsappLink() {
        return advisorWhatsappLink;
    }

    public void setAdvisorWhatsappLink(String advisorWhatsappLink) {
        this.advisorWhatsappLink = advisorWhatsappLink;
    }
    
    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
