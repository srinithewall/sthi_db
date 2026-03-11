package com.sthi.re.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sthi.exception.BusinessException;
import com.sthi.exception.ErrorCode;
import com.sthi.model.Users;
import com.sthi.re.model.ReLead;
import com.sthi.re.model.Project;
import com.sthi.re.model.Property;
import com.sthi.re.repo.ProjectRepository;
import com.sthi.re.repo.PropertyRepository;
import com.sthi.re.repo.ReLeadRepository;

@Service
public class LeadService {

    private static final Logger log =
            LoggerFactory.getLogger(LeadService.class);

    @Value("${app.rate-limit.enabled:true}")
    private boolean rateLimitEnabled;

    private static final String STATUS_NEW = "NEW";
    private static final String SOURCE_WEB = "WEB";

    private final LeadRoutingService routingService;
    private final ReLeadRepository leadRepo;
    private final PropertyRepository propertyRepository;
    private final ProjectRepository projectRepository;
    private final RateLimitService rateLimitService;
    private final RecaptchaService recaptchaService;
    private final LeadNotificationService notificationService;

    public LeadService(
            LeadRoutingService routingService,
            ReLeadRepository leadRepo,
            PropertyRepository propertyRepository,
            ProjectRepository projectRepository,
            RateLimitService rateLimitService,
            RecaptchaService recaptchaService,
            LeadNotificationService notificationService
    ) {
        this.routingService = routingService;
        this.leadRepo = leadRepo;
        this.propertyRepository = propertyRepository;
        this.projectRepository = projectRepository;
        this.rateLimitService = rateLimitService;
        this.recaptchaService = recaptchaService;
        this.notificationService = notificationService;
    }

    public ReLead createLead(
            String fullName,
            String phone,
            String email,
            String message,
            Long projectId,
            Long propertyId,
            Integer loggedInUserId,
            String ipAddress,
            String recaptchaToken
    ) {

        // 1️⃣ reCAPTCHA validation
        if (recaptchaToken == null || recaptchaToken.isBlank()) {
            throw new BusinessException(
                    ErrorCode.RECAPTCHA_FAILED,
                    "reCAPTCHA verification failed"
            );
        }

        recaptchaService.validateToken(recaptchaToken);

        // 2️⃣ Rate limit check
        if (rateLimitEnabled) {
            try {
                rateLimitService.checkRateLimit(ipAddress, phone);
            } catch (Exception e) {
                throw new BusinessException(
                        ErrorCode.RATE_LIMIT_EXCEEDED,
                        "Too many requests. Please try again later."
                );
            }
        }

        // 3️⃣ Determine effective project
        Long effectiveProjectId = projectId;

        if (projectId == null && propertyId != null) {
            effectiveProjectId = propertyRepository
                    .findById(propertyId)
                    .orElseThrow(() ->
                            new BusinessException(
                                    ErrorCode.INVALID_REQUEST,
                                    "Invalid property ID"
                            )
                    )
                    .getProjectId();
        }

        // 4️⃣ Duplicate check
        if (effectiveProjectId != null &&
                leadRepo.existsByPhoneAndProjectId(phone, effectiveProjectId)) {

            throw new BusinessException(
                    ErrorCode.LEAD_DUPLICATE,
                    "Lead already exists for this project"
            );
        }

        // 5️⃣ Resolve advisor
        Users advisor = routingService.resolveAdvisor(effectiveProjectId);

        // 6️⃣ Create Lead Entity
        LocalDateTime now = LocalDateTime.now();

        ReLead lead = new ReLead();
        lead.setFullName(fullName != null ? fullName.trim() : null);
        lead.setPhone(phone != null ? phone.trim() : null);
        lead.setEmail(email != null ? email.trim() : null);
        lead.setMessage(message != null ? message.trim() : null);

        lead.setProjectId(effectiveProjectId);
        lead.setPropertyId(propertyId);

        lead.setAdvisorId(advisor.getId());
        lead.setSource(SOURCE_WEB);
        lead.setStatus(STATUS_NEW);

        lead.setCreatedAt(now);
        lead.setUpdatedAt(now);
        lead.setRequestedByUserId(loggedInUserId);

        // Generate advisor WhatsApp link
        lead.setAdvisorWhatsappLink(
                generateAdvisorWhatsappLink(lead, advisor)
        );

        // Save to DB
        ReLead savedLead = leadRepo.save(lead);

        log.info("Lead saved successfully with ID: {}", savedLead.getId());

        // 7️⃣ Fetch Project & Property Names (optional)
        String projectName = null;
        String propertyName = null;

        if (effectiveProjectId != null) {
        	projectName = projectRepository.findById(effectiveProjectId)
        	        .map(p -> p.getProjectName())   // adjust to your real getter
        	        .orElse(null);
        }

        if (propertyId != null) {
            propertyName = propertyRepository.findById(propertyId)
            		.map(p -> p.getPropertyName())
                    .orElse(null);
        }

        // 8️⃣ Trigger Async Notifications
        notificationService.triggerNotifications(
                savedLead,
                advisor,
                projectName,
                propertyName
        );

        return savedLead;
    }

    private String generateAdvisorWhatsappLink(
            ReLead lead,
            Users advisor
    ) {

        String context;

        if (lead.getProjectId() != null) {
            context = "Project ID " + lead.getProjectId();
        } else if (lead.getPropertyId() != null) {
            context = "Property ID " + lead.getPropertyId();
        } else {
            context = "your enquiry";
        }

        String message = String.format(
                "Hi %s,\n\n"
              + "You enquired about %s.\n"
              + "I am your property advisor. When would be a good time to call you?\n\n"
              + "— %s",
                lead.getFullName(),
                context,
                advisor.getFirstName()
        );

        String encoded =
                URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "https://wa.me/"
                + advisor.getPhoneNumber()
                + "?text=" + encoded;
    }
}