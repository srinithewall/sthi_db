package com.sthi.re.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sthi.model.Users;
import com.sthi.re.model.ReLead;

@Service
public class LeadNotificationService {

    private static final Logger log =
            LoggerFactory.getLogger(LeadNotificationService.class);

    private final TelegramNotificationService telegramService;
    private final EmailService emailService;

    public LeadNotificationService(
            TelegramNotificationService telegramService,
            EmailService emailService
    ) {
        this.telegramService = telegramService;
        this.emailService = emailService;
    }

    @Async
    public void triggerNotifications(
            ReLead lead,
            Users advisor,
            String projectName,
            String propertyName
    ) {

        log.info("Async notification started for lead ID: {}", lead.getId());

        try {

            StringBuilder message = new StringBuilder();

            message.append("🚀 *NEW LEAD ASSIGNED*\n\n")
                   .append("👤 *Advisor:* ").append(advisor.getFirstName()).append("\n")
                   .append("🙋 *Customer:* ").append(lead.getFullName()).append("\n")
                   .append("📞 *Phone:* ").append(lead.getPhone()).append("\n\n");

            if (projectName != null && !projectName.isBlank()) {
                message.append("🏢 *Project:* ").append(projectName).append("\n");
            }

            if (propertyName != null && !propertyName.isBlank()) {
                message.append("🏠 *Property:* ").append(propertyName).append("\n");
            }

            message.append("\n━━━━━━━━━━━━━━━━━━\n")
                   .append("📲 *Tap below to contact*\n\n")
                   .append("📞 Call: tel:").append(lead.getPhone()).append("\n")
                   .append("💬 WhatsApp: https://wa.me/91")
                   .append(lead.getPhone());

            // Send formatted Telegram message
            telegramService.sendFormattedMessage(message.toString());

            // Optional Email Notification (plain text version)
            if (emailService != null) {
                emailService.sendEmail(
                        advisor.getEmail(),
                        "🔥 New Lead Assigned",
                        message.toString().replace("*", "")
                );
            }

        } catch (Exception ex) {
            log.error("Notification failed for lead ID {}", lead.getId(), ex);
        }
    }
}