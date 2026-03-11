package com.sthi.controller;

import com.sthi.dto.NotificationDTO;
import com.sthi.dto.request.ApiResponse;
import com.sthi.model.Notification;
import com.sthi.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    
    @Autowired
    private NotificationService notificationService;
    
    /**
     * GET /api/notifications - Get paginated notifications
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getNotifications(
            @RequestHeader(value = "X-User-Id", required = false) Long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String type) {
        
        logger.info("GET /api/notifications - userId: {}, page: {}, size: {}, type: {}", 
                   userId, page, size, type);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getNotifications request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            // Validate pagination parameters
            if (page < 0) {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Page must be greater than or equal to 0"));
            }
            
            if (size <= 0 || size > 100) {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Size must be between 1 and 100"));
            }
            
            Page<Notification> notifications = notificationService.getUserNotifications(userId, page, size, type);
            
            // Convert to DTO
            List<NotificationDTO> notificationDTOs = notifications.getContent()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            
            Map<String, Object> response = new HashMap<>();
            response.put("notifications", notificationDTOs);
            response.put("pagination", Map.of(
                "current_page", page,
                "total_pages", notifications.getTotalPages(),
                "total_count", notifications.getTotalElements(),
                "has_next", notifications.hasNext(),
                "has_prev", page > 0
            ));
            
            logger.info("Returning {} notifications for user {} (page {})", 
                       notificationDTOs.size(), userId, page);
            
            return ResponseEntity.ok(ApiResponse.success(response));
            
        } catch (Exception e) {
            logger.error("Error fetching notifications for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch notifications: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/notifications/recent - Get recent notifications for dashboard
     */
    @GetMapping("/recent")
    public ResponseEntity<ApiResponse<List<NotificationDTO>>> getRecentNotifications(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/notifications/recent - userId: {}", userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getRecentNotifications request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            List<Notification> notifications = notificationService.getRecentNotifications(userId, 5);
            List<NotificationDTO> notificationDTOs = notifications.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            
            logger.info("Returning {} recent notifications for user {}", notificationDTOs.size(), userId);
            return ResponseEntity.ok(ApiResponse.success(notificationDTOs));
            
        } catch (Exception e) {
            logger.error("Error fetching recent notifications for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch recent notifications: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/notifications/unread-count - Get unread notifications count
     */
    @GetMapping("/unread-count")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getUnreadCount(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/notifications/unread-count - userId: {}", userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getUnreadCount request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            Long unreadCount = notificationService.getUnreadCount(userId);
            
            logger.info("User {} has {} unread notifications", userId, unreadCount);
            return ResponseEntity.ok(ApiResponse.success(Map.of("unread_count", unreadCount)));
            
        } catch (Exception e) {
            logger.error("Error fetching unread count for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch unread count: " + e.getMessage()));
        }
    }
    
    /**
     * PUT /api/notifications/{id}/read - Mark notification as read
     */
    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<String>> markAsRead(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("PUT /api/notifications/{}/read - userId: {}", id, userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in markAsRead request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            if (id == null || id <= 0) {
                logger.warn("Invalid notification ID: {}", id);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid notification ID"));
            }
            
            boolean success = notificationService.markAsRead(id, userId);
            if (success) {
                logger.info("Successfully marked notification {} as read for user {}", id, userId);
                return ResponseEntity.ok(ApiResponse.success("Notification marked as read"));
            } else {
                logger.warn("Notification {} not found or access denied for user {}", id, userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Notification not found or access denied"));
            }
            
        } catch (Exception e) {
            logger.error("Error marking notification {} as read for user {}: {}", id, userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to mark notification as read: " + e.getMessage()));
        }
    }
    
    /**
     * PUT /api/notifications/read-all - Mark all notifications as read
     */
    @PutMapping("/read-all")
    public ResponseEntity<ApiResponse<String>> markAllAsRead(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("PUT /api/notifications/read-all - userId: {}", userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in markAllAsRead request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            boolean success = notificationService.markAllAsRead(userId);
            if (success) {
                logger.info("Successfully marked all notifications as read for user {}", userId);
                return ResponseEntity.ok(ApiResponse.success("All notifications marked as read"));
            } else {
                logger.info("No unread notifications to mark for user {}", userId);
                return ResponseEntity.ok(ApiResponse.success("No unread notifications to mark"));
            }
            
        } catch (Exception e) {
            logger.error("Error marking all notifications as read for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to mark all notifications as read: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/notifications/unread - Get all unread notifications
     */
    @GetMapping("/unread")
    public ResponseEntity<ApiResponse<List<NotificationDTO>>> getUnreadNotifications(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/notifications/unread - userId: {}", userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getUnreadNotifications request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            List<Notification> notifications = notificationService.getUnreadNotifications(userId);
            List<NotificationDTO> notificationDTOs = notifications.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            
            logger.info("Returning {} unread notifications for user {}", notificationDTOs.size(), userId);
            return ResponseEntity.ok(ApiResponse.success(notificationDTOs));
            
        } catch (Exception e) {
            logger.error("Error fetching unread notifications for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch unread notifications: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/notifications/test - Test endpoint for debugging
     */
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/notifications/test - userId: {}", userId);
        
        Map<String, Object> response = new HashMap<>();
        
        if (userId == null) {
            response.put("status", "error");
            response.put("message", "X-User-Id header is missing");
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            logger.warn("Test endpoint called without X-User-Id header");
            return ResponseEntity.badRequest().body(response);
        }
        
        if (userId <= 0) {
            response.put("status", "error");
            response.put("message", "Invalid user ID: " + userId);
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            logger.warn("Test endpoint called with invalid user ID: {}", userId);
            return ResponseEntity.badRequest().body(response);
        }
        
        response.put("status", "success");
        response.put("message", "Notification API is working correctly");
        response.put("userId", userId);
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("endpoints", List.of(
            "GET /api/notifications",
            "GET /api/notifications/recent", 
            "GET /api/notifications/unread-count",
            "PUT /api/notifications/{id}/read",
            "PUT /api/notifications/read-all",
            "GET /api/notifications/unread"
        ));
        
        logger.info("Test endpoint successful for user {}", userId);
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/notifications/{id} - Get specific notification by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationDTO>> getNotificationById(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/notifications/{} - userId: {}", id, userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getNotificationById request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            if (id == null || id <= 0) {
                logger.warn("Invalid notification ID: {}", id);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid notification ID"));
            }
            
            // Check if notification exists and belongs to user
            java.util.Optional<Notification> notificationOpt = notificationService.getNotificationById(id);
            if (notificationOpt.isEmpty()) {
                logger.warn("Notification {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Notification not found"));
            }
            
            Notification notification = notificationOpt.get();
            if (!notification.getUserId().equals(userId)) {
                logger.warn("User {} attempted to access notification {} owned by user {}", 
                           userId, id, notification.getUserId());
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(ApiResponse.error("Access denied"));
            }
            
            NotificationDTO notificationDTO = convertToDTO(notification);
            logger.info("Returning notification {} for user {}", id, userId);
            return ResponseEntity.ok(ApiResponse.success(notificationDTO));
            
        } catch (Exception e) {
            logger.error("Error fetching notification {} for user {}: {}", id, userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch notification: " + e.getMessage()));
        }
    }
    
    /**
     * Helper method to convert Notification entity to NotificationDTO
     */
    private NotificationDTO convertToDTO(Notification notification) {
        if (notification == null) {
            return null;
        }
        
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setIsRead(notification.getIsRead());
        dto.setMetadata(notification.getMetadata());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }
}