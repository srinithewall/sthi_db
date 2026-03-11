package com.sthi.dto;

public class WalletBalanceResponse {
    private Integer availableBalance;
    private Integer lockedBalance;
    private Integer totalBalance;
    
    public WalletBalanceResponse(Integer availableBalance, Integer lockedBalance) {
        this.availableBalance = availableBalance;
        this.lockedBalance = lockedBalance;
        this.totalBalance = availableBalance + lockedBalance;
    }
    
    // Getters
    public Integer getAvailableBalance() { return availableBalance; }
    public Integer getLockedBalance() { return lockedBalance; }
    public Integer getTotalBalance() { return totalBalance; }
    
    // Setters (if needed)
    public void setAvailableBalance(Integer availableBalance) { this.availableBalance = availableBalance; }
    public void setLockedBalance(Integer lockedBalance) { this.lockedBalance = lockedBalance; }
    public void setTotalBalance(Integer totalBalance) { this.totalBalance = totalBalance; }
}