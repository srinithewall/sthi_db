package com.sthi.service;



import com.sthi.dto.WalletBalanceResponse;
import com.sthi.model.Wallet;
import com.sthi.repo.WalletRepo;
import com.sthi.repo.InvestmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletService {
    
    @Autowired
    private WalletRepo walletRepo;
    
    @Autowired
    private InvestmentRepo investmentRepo;
    
    public WalletBalanceResponse getWalletBalance(Integer userId) {
        try {
            // Get available balance from wallet table
            Wallet wallet = walletRepo.findByUserId(userId);
            Integer availableBalance = (wallet != null) ? wallet.getAvailableBalance() : 0;
            
            // Calculate locked balance from active investments
            // Assuming status = 1 means active investments in your table
            Integer lockedBalance = investmentRepo.sumActiveInvestmentAmountByUserId(userId);
            
            return new WalletBalanceResponse(availableBalance, lockedBalance);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch wallet balance: " + e.getMessage());
        }
    }
    
    // Helper method to get or create wallet for a user
    public Wallet getOrCreateWallet(Integer userId) {
        Wallet wallet = walletRepo.findByUserId(userId);
        if (wallet == null) {
            wallet = new Wallet(userId);
            wallet = walletRepo.save(wallet);
        }
        return wallet;
    }
}