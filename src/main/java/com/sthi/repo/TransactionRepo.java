package com.sthi.repo;



import com.sthi.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    
    // Find all transactions for a user, ordered by latest first
    List<Transaction> findByUserIdOrderByCreatedAtDesc(Integer userId);
    
    // Find paginated transactions for a user
    Page<Transaction> findByUserIdOrderByCreatedAtDesc(Integer userId, Pageable pageable);
    
    // Find recent transactions with limit
    @Query("SELECT t FROM Transaction t WHERE t.userId = :userId ORDER BY t.createdAt DESC")
    List<Transaction> findRecentTransactionsByUserId(@Param("userId") Integer userId, Pageable pageable);
    
    // Count transactions for a user
    Long countByUserId(Integer userId);
    
    // Find transactions by type
    List<Transaction> findByUserIdAndTypeOrderByCreatedAtDesc(Integer userId, String type);
}
