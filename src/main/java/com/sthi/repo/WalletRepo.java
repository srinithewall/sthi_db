package com.sthi.repo;



import com.sthi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {
    Wallet findByUserId(Integer userId);
    
    @Query("SELECT w FROM Wallet w WHERE w.userId = :userId")
    Wallet findWalletByUserId(@Param("userId") Integer userId);
}
