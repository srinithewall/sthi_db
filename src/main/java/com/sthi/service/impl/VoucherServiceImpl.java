package com.sthi.service.impl;

import com.sthi.model.Voucher;
import com.sthi.repo.VoucherRepo;
import com.sthi.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepo voucherRepo;

    @Override
    public List<Voucher> getActiveVouchersForUser(Long userId) {
        return voucherRepo.findActiveVouchersForUser(userId);
    }

    @Override
    public List<Voucher> getActiveVouchersForUserByType(Long userId, String voucherType) {
        return voucherRepo.findActiveVouchersForUserByType(userId, voucherType.toUpperCase());
    }
}