package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sthi.re.model.ReLead;

public interface ReLeadRepository extends JpaRepository<ReLead, Long> {

    // Duplicate lead check (same phone + project)
    boolean existsByPhoneAndProjectId(String phone, Long projectId);

    // Advisor workload (future: round-robin / least load)
    List<ReLead> findByAdvisorIdAndStatus(Long advisorId, String status);

    // Fetch the latest pending lead for verification
    java.util.Optional<ReLead> findTopByPhoneAndStatusOrderByCreatedAtDesc(String phone, String status);
}
