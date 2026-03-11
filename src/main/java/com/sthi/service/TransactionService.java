package com.sthi.service;

import com.sthi.dto.TransactionResponse;
import com.sthi.model.Transaction;
import com.sthi.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepo transactionRepository;
    
    /**
     * Get recent transactions for a user (limited by count)
     */
    public List<TransactionResponse> getRecentTransactions(Integer userId, int limit) {
        try {
            Pageable pageable = PageRequest.of(0, limit);
            Page<Transaction> transactions = transactionRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
            
            // Convert to response using traditional loop instead of streams
            List<TransactionResponse> responseList = new ArrayList<>();
            for (Transaction transaction : transactions) {
                responseList.add(new TransactionResponse(transaction));
            }
            return responseList;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching recent transactions for user: " + userId, e);
        }
    }
    
    /**
     * Get all transactions for a user
     */
    public List<TransactionResponse> getAllTransactions(Integer userId) {
        try {
            List<Transaction> transactions = transactionRepository.findByUserIdOrderByCreatedAtDesc(userId);
            
            // Convert to response using traditional loop
            List<TransactionResponse> responseList = new ArrayList<>();
            for (Transaction transaction : transactions) {
                responseList.add(new TransactionResponse(transaction));
            }
            return responseList;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all transactions for user: " + userId, e);
        }
    }
    
    /**
     * Get transactions by type for a user
     */
    public List<TransactionResponse> getTransactionsByType(Integer userId, String type) {
        try {
            List<Transaction> transactions = transactionRepository.findByUserIdAndTypeOrderByCreatedAtDesc(userId, type);
            
            // Convert to response using traditional loop
            List<TransactionResponse> responseList = new ArrayList<>();
            for (Transaction transaction : transactions) {
                responseList.add(new TransactionResponse(transaction));
            }
            return responseList;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching transactions by type for user: " + userId, e);
        }
    }
    
    /**
     * Get transaction count for a user
     */
    public Long getTransactionCount(Integer userId) {
        try {
            return transactionRepository.countByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching transaction count for user: " + userId, e);
        }
    }
    
    /**
     * Get transactions with pagination
     */
    public List<TransactionResponse> getTransactionsWithPagination(Integer userId, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Transaction> transactions = transactionRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable).getContent();
            
            // Convert to response using traditional loop
            List<TransactionResponse> responseList = new ArrayList<>();
            for (Transaction transaction : transactions) {
                responseList.add(new TransactionResponse(transaction));
            }
            return responseList;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching paginated transactions for user: " + userId, e);
        }
    }
    
    /**
     * Get total transaction amount by type for a user
     */
    public Double getTotalAmountByType(Integer userId, String type) {
        try {
            List<Transaction> transactions = transactionRepository.findByUserIdAndTypeOrderByCreatedAtDesc(userId, type);
            
            // Calculate sum using traditional loop instead of streams
            double total = 0.0;
            for (Transaction transaction : transactions) {
                if (transaction.getAmount() != null) {
                    total += transaction.getAmount();
                }
            }
            return total;
        } catch (Exception e) {
            throw new RuntimeException("Error calculating total amount by type for user: " + userId, e);
        }
    }
}