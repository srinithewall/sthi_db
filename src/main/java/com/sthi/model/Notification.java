package com.sthi.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(name = "user_id", nullable = false)
 private Long userId;
 
 @Column(nullable = false)
 private String title;
 
 @Column(nullable = false, columnDefinition = "TEXT")
 private String message;
 
 @Column(nullable = false)
 private String type; // investment_scheme, shopping_product, wallet, voucher, system
 
 @Column(name = "is_read")
 private Boolean isRead = false;
 
 @Column(columnDefinition = "JSON")
 private String metadata;
 
 @CreationTimestamp
 @Column(name = "created_at", updatable = false)
 private LocalDateTime createdAt;
 
 @UpdateTimestamp
 @Column(name = "updated_at")
 private LocalDateTime updatedAt;
 
 // Constructors
 public Notification() {}
 
 public Notification(Long userId, String title, String message, String type) {
     this.userId = userId;
     this.title = title;
     this.message = message;
     this.type = type;
 }
 
 // Getters and Setters
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 
 public Long getUserId() { return userId; }
 public void setUserId(Long userId) { this.userId = userId; }
 
 public String getTitle() { return title; }
 public void setTitle(String title) { this.title = title; }
 
 public String getMessage() { return message; }
 public void setMessage(String message) { this.message = message; }
 
 public String getType() { return type; }
 public void setType(String type) { this.type = type; }
 
 @JsonProperty("is_read")
 public Boolean getIsRead() { return isRead; }
 public void setIsRead(Boolean isRead) { this.isRead = isRead; }
 
 public String getMetadata() { return metadata; }
 public void setMetadata(String metadata) { this.metadata = metadata; }
 
 public LocalDateTime getCreatedAt() { return createdAt; }
 public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
 
 public LocalDateTime getUpdatedAt() { return updatedAt; }
 public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}