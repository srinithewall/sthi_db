package com.sthi.controller;



import com.sthi.dto.TransactionResponse;
import com.sthi.dto.request.ApiResponse;
import com.sthi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    /**
     * Get recent transactions (limited to 5 by default)
     * GET /api/transactions/recent
     */
    @GetMapping("/recent")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getRecentTransactions(
            @RequestHeader("X-User-Id") Integer userId,
            @RequestParam(defaultValue = "5") int limit) {
        try {
            List<TransactionResponse> transactions = transactionService.getRecentTransactions(userId, limit);
            return ResponseEntity.ok(ApiResponse.success("Recent transactions fetched successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch recent transactions: " + e.getMessage()));
        }
    }
    
    /**
     * Get all transactions for a user
     * GET /api/transactions
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactions(
            @RequestHeader("X-User-Id") Integer userId) {
        try {
            List<TransactionResponse> transactions = transactionService.getAllTransactions(userId);
            return ResponseEntity.ok(ApiResponse.success("All transactions fetched successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch transactions: " + e.getMessage()));
        }
    }
    
    /**
     * Get transactions by type
     * GET /api/transactions/type/{type}
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionsByType(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable String type) {
        try {
            List<TransactionResponse> transactions = transactionService.getTransactionsByType(userId, type);
            return ResponseEntity.ok(ApiResponse.success("Transactions fetched by type successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch transactions by type: " + e.getMessage()));
        }
    }
    
    /**
     * Get transaction count for a user
     * GET /api/transactions/count
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getTransactionCount(
            @RequestHeader("X-User-Id") Integer userId) {
        try {
            Long count = transactionService.getTransactionCount(userId);
            return ResponseEntity.ok(ApiResponse.success("Transaction count fetched successfully", count));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch transaction count: " + e.getMessage()));
        }
    }
    
    /**
     * Get transactions with pagination
     * GET /api/transactions/paginated?page=0&size=10
     */
    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionsWithPagination(
            @RequestHeader("X-User-Id") Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<TransactionResponse> transactions = transactionService.getTransactionsWithPagination(userId, page, size);
            return ResponseEntity.ok(ApiResponse.success("Paginated transactions fetched successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch paginated transactions: " + e.getMessage()));
        }
    }
    
    /**
     * Get total amount by transaction type
     * GET /api/transactions/total-amount/{type}
     */
    @GetMapping("/total-amount/{type}")
    public ResponseEntity<ApiResponse<Double>> getTotalAmountByType(
            @RequestHeader("X-User-Id") Integer userId,
            @PathVariable String type) {
        try {
            Double totalAmount = transactionService.getTotalAmountByType(userId, type);
            return ResponseEntity.ok(ApiResponse.success("Total amount calculated successfully", totalAmount));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to calculate total amount: " + e.getMessage()));
        }
    }
    
    /**
     * Get transaction summary for dashboard
     * GET /api/transactions/summary
     */
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTransactionSummary(
            @RequestHeader("X-User-Id") Integer userId) {
        try {
            Map<String, Object> summary = new HashMap<>();
            
            // Get counts
            summary.put("totalTransactions", transactionService.getTransactionCount(userId));
            summary.put("recentTransactions", transactionService.getRecentTransactions(userId, 5));
            
            // Get total amounts by type
            summary.put("totalDeposits", transactionService.getTotalAmountByType(userId, "deposit"));
            summary.put("totalWithdrawals", transactionService.getTotalAmountByType(userId, "withdrawal"));
            summary.put("totalInvestments", transactionService.getTotalAmountByType(userId, "investment"));
            summary.put("totalVouchers", transactionService.getTotalAmountByType(userId, "voucher"));
            summary.put("totalRefunds", transactionService.getTotalAmountByType(userId, "refund"));
            
            return ResponseEntity.ok(ApiResponse.success("Transaction summary fetched successfully", summary));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch transaction summary: " + e.getMessage()));
        }
    }
}
