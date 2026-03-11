// NotificationService.java
package com.sthi.service;

import com.sthi.model.Notification;
import com.sthi.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepo notificationRepo;
    
    /**
     * Get paginated notifications for a user with optional type filter
     */
    public Page<Notification> getUserNotifications(Long userId, Integer page, Integer size, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        
        if (type != null && !type.trim().isEmpty()) {
            return notificationRepo.findByUserIdAndType(userId, type, pageable);
        } else {
            return notificationRepo.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        }
    }
    
    /**
     * Get recent notifications for dashboard (limited to specified count)
     */
    public List<Notification> getRecentNotifications(Long userId, int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("createdAt").descending());
        return notificationRepo.findRecentNotifications(userId, pageable);
    }
    
    /**
     * Get unread notifications count for a user
     */
    public Long getUnreadCount(Long userId) {
        return notificationRepo.countByUserIdAndIsReadFalse(userId);
    }
    
    /**
     * Get all unread notifications for a user
     */
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepo.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
    }
    
    /**
     * Mark a specific notification as read
     */
    public boolean markAsRead(Long notificationId, Long userId) {
        int updated = notificationRepo.markAsRead(notificationId, userId);
        return updated > 0;
    }
    
    /**
     * Mark all notifications as read for a user
     */
    public boolean markAllAsRead(Long userId) {
        int updated = notificationRepo.markAllAsRead(userId);
        return updated > 0;
    }
    
    /**
     * Create a new notification
     */
    public Notification createNotification(Notification notification) {
        return notificationRepo.save(notification);
    }
    
    /**
     * Check if notification belongs to user
     */
    public boolean isNotificationOwnedByUser(Long notificationId, Long userId) {
        return notificationRepo.findById(notificationId)
                .map(notification -> notification.getUserId().equals(userId))
                .orElse(false);
    }
    
    public java.util.Optional<Notification> getNotificationById(Long id) {
        return notificationRepo.findById(id);
    }
}