package com.sthi.re.service;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sthi.re.model.ReLeadRoutingConfig;
import com.sthi.model.Users;
import com.sthi.re.repo.ReLeadRoutingConfigRepository;
import com.sthi.re.repo.UserRepository;

@Service
public class LeadRoutingService {

	private static final Integer ADVISOR_USER_TYPE_ID = 2;
    private static final Integer ACTIVE = 1;

    private final ReLeadRoutingConfigRepository routingRepo;
    private final UserRepository userRepo;

    public LeadRoutingService(
            ReLeadRoutingConfigRepository routingRepo,
            UserRepository userRepo
    ) {
        this.routingRepo = routingRepo;
        this.userRepo = userRepo;
    }

    @Cacheable(value = "advisorRouting",key = "#projectId != null ? #projectId : 'GLOBAL'")
    public Users resolveAdvisor(Long projectId) {

        // 1️⃣ Project-specific routing
        Optional<ReLeadRoutingConfig> projectRule =
                routingRepo.findFirstByProjectIdAndIsActive(projectId, ACTIVE);

        if (projectRule.isPresent()) {
            return validateAdvisor(projectRule.get().getFixedAdvisorId());
        }

        // 2️⃣ Global routing
        Optional<ReLeadRoutingConfig> globalRule =
                routingRepo.findFirstByProjectIdIsNullAndIsActive(ACTIVE);

        if (globalRule.isPresent()) {
            return validateAdvisor(globalRule.get().getFixedAdvisorId());
        }

        throw new IllegalStateException(
                "No active lead routing configuration found"
        );
    }

    private Users validateAdvisor(Integer advisorId) {

        Users advisor = userRepo
                .findByIdAndIsActive(advisorId, ACTIVE)
                .orElseThrow(() ->
                        new IllegalStateException("Advisor not found or inactive")
                );

        if (advisor.getUserTypeId() == null ||
        	    !advisor.getUserTypeId().equals(ADVISOR_USER_TYPE_ID)) {

        	    throw new IllegalStateException(
        	        "User is not an advisor: " + advisor.getId()
        	    );
        	}

        return advisor;
    }
}
