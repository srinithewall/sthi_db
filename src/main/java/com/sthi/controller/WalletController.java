package com.sthi.controller;



import com.sthi.dto.WalletBalanceResponse;
import com.sthi.dto.request.ApiResponse;
import com.sthi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WalletController {
    
    @Autowired
    private WalletService walletService;
    
    @GetMapping("/wallet/balance")
    public ResponseEntity<ApiResponse<WalletBalanceResponse>> getWalletBalance(
            @RequestHeader("X-User-Id") Integer userId) {
        try {
            WalletBalanceResponse balance = walletService.getWalletBalance(userId);
            return ResponseEntity.ok(ApiResponse.success(balance));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to fetch wallet balance: " + e.getMessage()));
        }
    }
}