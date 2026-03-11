package com.sthi.dto;

import java.time.LocalDateTime;

public class VoucherDTO {
    private Long id;
    private String voucherCode;
    private String title;
    private String description;
    private Double amount;
    private String voucherType;
    private LocalDateTime expiryDate;
    private Boolean isActive;
    private Boolean isUsed;
    private LocalDateTime createdAt;
    
    // Constructors
    public VoucherDTO() {}
    
    public VoucherDTO(Long id, String voucherCode, String title, String description, 
                     Double amount, String voucherType, LocalDateTime expiryDate, 
                     Boolean isActive, Boolean isUsed, LocalDateTime createdAt) {
        this.id = id;
        this.voucherCode = voucherCode;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.voucherType = voucherType;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
        this.isUsed = isUsed;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getVoucherType() { return voucherType; }
    public void setVoucherType(String voucherType) { this.voucherType = voucherType; }
    
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Boolean getIsUsed() { return isUsed; }
    public void setIsUsed(Boolean isUsed) { this.isUsed = isUsed; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}