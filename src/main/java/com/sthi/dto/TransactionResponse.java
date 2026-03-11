package com.sthi.dto;



import java.time.LocalDateTime;

public class TransactionResponse {
    private Long id;
    private Double amount;
    private String type;
    private String description;
    private String referenceId;
    private String status;
    private LocalDateTime createdAt;
    private String formattedDate;
    private Boolean isCredit;
    
    // Constructor from Transaction entity
    public TransactionResponse(com.sthi.model.Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.description = transaction.getDescription();
        this.referenceId = transaction.getReferenceId();
        this.status = transaction.getStatus();
        this.createdAt = transaction.getCreatedAt();
        this.formattedDate = formatDate(transaction.getCreatedAt());
        this.isCredit = isCreditTransaction(transaction.getType());
    }
    
    private String formatDate(LocalDateTime date) {
        // Simple date formatting without DateTimeFormatter
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        
        int day = date.getDayOfMonth();
        String month = months[date.getMonthValue() - 1];
        int year = date.getYear();
        int hour = date.getHour();
        int minute = date.getMinute();
        
        String amPm = hour >= 12 ? "PM" : "AM";
        int displayHour = hour > 12 ? hour - 12 : hour;
        if (displayHour == 0) displayHour = 12; // Handle midnight
        
        return String.format("%d %s %d, %02d:%02d %s", 
                           day, month, year, displayHour, minute, amPm);
    }
    
    private Boolean isCreditTransaction(String type) {
        return "deposit".equals(type) || 
               "voucher".equals(type) || 
               "refund".equals(type);
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getReferenceId() { return referenceId; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public String getFormattedDate() { return formattedDate; }
    public void setFormattedDate(String formattedDate) { this.formattedDate = formattedDate; }
    
    public Boolean getIsCredit() { return isCredit; }
    public void setIsCredit(Boolean isCredit) { this.isCredit = isCredit; }
}
