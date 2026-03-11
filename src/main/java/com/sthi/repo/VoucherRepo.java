package com.sthi.repo;

import com.sthi.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepo extends JpaRepository<Voucher, Long> {
    
    // Find active vouchers for a specific user AND generic vouchers (user_id = 0)
    @Query("SELECT v FROM Voucher v WHERE v.isActive = true AND (v.userId = :userId OR v.userId = 0)")
    List<Voucher> findActiveVouchersForUser(@Param("userId") Long userId);
    
    // Find active vouchers by type for a specific user AND generic vouchers
    @Query("SELECT v FROM Voucher v WHERE v.isActive = true AND (v.userId = :userId OR v.userId = 0) AND v.voucherType = :voucherType")
    List<Voucher> findActiveVouchersForUserByType(@Param("userId") Long userId, @Param("voucherType") String voucherType);
}