package com.sthi.service;

import com.sthi.model.Voucher;
import java.util.List;

public interface VoucherService {
    
    // Get active vouchers for user (including generic vouchers with user_id=0)
    List<Voucher> getActiveVouchersForUser(Long userId);
    
    // Get active vouchers by type for user (including generic vouchers)
    List<Voucher> getActiveVouchersForUserByType(Long userId, String voucherType);
}