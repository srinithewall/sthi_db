package com.sthi.re.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sthi.re.model.ReLeadRoutingConfig;

public interface ReLeadRoutingConfigRepository
        extends JpaRepository<ReLeadRoutingConfig, Long> {

    // Project-specific active routing rule
    Optional<ReLeadRoutingConfig>
        findFirstByProjectIdAndIsActive(Long projectId, Integer isActive);

    // Global (default) active routing rule
    Optional<ReLeadRoutingConfig>
        findFirstByProjectIdIsNullAndIsActive(Integer isActive);
}
