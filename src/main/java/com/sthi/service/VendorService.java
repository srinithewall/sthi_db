package com.sthi.service;

import com.sthi.model.Vendor;
import com.sthi.repo.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepo VendorRepo;

    public List<Vendor> getAllVendors() {
        return VendorRepo.findAllByOrderByNameAsc();
    }

    public Optional<Vendor> getVendorById(Long id) {
        return VendorRepo.findById(id);
    }

    public Vendor saveVendor(Vendor vendor) {
        return VendorRepo.save(vendor);
    }

    public void deleteVendor(Long id) {
        VendorRepo.deleteById(id);
    }
}