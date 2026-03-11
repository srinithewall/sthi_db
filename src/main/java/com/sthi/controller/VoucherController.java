package com.sthi.controller;

import com.sthi.dto.request.ApiResponse;
import com.sthi.model.Voucher;
import com.sthi.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {
    
    private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);
    
    @Autowired
    private VoucherService voucherService;
    
    /**
     * GET /api/vouchers - Get active vouchers for user (including generic vouchers with user_id=0)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getActiveVouchers(
            @RequestHeader(value = "X-User-Id", required = false) Long userId,
            @RequestParam(required = false) String type) {
        
        logger.info("GET /api/vouchers - userId: {}, type: {}", userId, type);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getActiveVouchers request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            List<Voucher> vouchers;
            if (type != null && !type.trim().isEmpty()) {
                vouchers = voucherService.getActiveVouchersForUserByType(userId, type.trim());
            } else {
                vouchers = voucherService.getActiveVouchersForUser(userId);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("vouchers", vouchers);
            response.put("count", vouchers.size());
            response.put("userId", userId);
            
            // Count personal vs generic vouchers
            long personalCount = vouchers.stream().filter(v -> v.getUserId().equals(userId)).count();
            long genericCount = vouchers.stream().filter(v -> v.getUserId() == 0).count();
            
            response.put("breakdown", Map.of(
                "personal_vouchers", personalCount,
                "generic_vouchers", genericCount
            ));
            
            logger.info("Returning {} active vouchers for user {} ({} personal, {} generic)", 
                       vouchers.size(), userId, personalCount, genericCount);
            
            return ResponseEntity.ok(ApiResponse.success(response));
            
        } catch (Exception e) {
            logger.error("Error fetching active vouchers for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch vouchers: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/vouchers/valid - Get valid active vouchers (not expired)
     */
    @GetMapping("/valid")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getValidActiveVouchers(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/vouchers/valid - userId: {}", userId);
        
        try {
            // Validate userId header
            if (userId == null) {
                logger.warn("Missing X-User-Id header in getValidActiveVouchers request");
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Missing required header: X-User-Id"));
            }
            
            if (userId <= 0) {
                logger.warn("Invalid user ID: {}", userId);
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid user ID"));
            }
            
            List<Voucher> allVouchers = voucherService.getActiveVouchersForUser(userId);
            
            // Filter out expired vouchers
            List<Voucher> validVouchers = allVouchers.stream()
                    .filter(v -> v.getExpiryDate().isAfter(java.time.LocalDateTime.now()))
                    .collect(java.util.stream.Collectors.toList());
            
            Map<String, Object> response = new HashMap<>();
            response.put("vouchers", validVouchers);
            response.put("count", validVouchers.size());
            response.put("userId", userId);
            
            // Count personal vs generic vouchers
            long personalCount = validVouchers.stream().filter(v -> v.getUserId().equals(userId)).count();
            long genericCount = validVouchers.stream().filter(v -> v.getUserId() == 0).count();
            
            response.put("breakdown", Map.of(
                "personal_vouchers", personalCount,
                "generic_vouchers", genericCount
            ));
            
            logger.info("Returning {} valid active vouchers for user {} ({} personal, {} generic)", 
                       validVouchers.size(), userId, personalCount, genericCount);
            
            return ResponseEntity.ok(ApiResponse.success(response));
            
        } catch (Exception e) {
            logger.error("Error fetching valid active vouchers for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch valid vouchers: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/vouchers/test - Test endpoint for debugging
     */
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint(
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        
        logger.info("GET /api/vouchers/test - userId: {}", userId);
        
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
        response.put("message", "Voucher API is working correctly");
        response.put("userId", userId);
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("endpoints", List.of(
            "GET /api/vouchers",
            "GET /api/vouchers?type=SHOPPING",
            "GET /api/vouchers/valid"
        ));
        
        logger.info("Test endpoint successful for user {}", userId);
        return ResponseEntity.ok(response);
    }
}