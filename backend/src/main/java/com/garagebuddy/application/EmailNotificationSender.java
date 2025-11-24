package com.garagebuddy.application;

import com.garagebuddy.domain.Reminder;
import com.garagebuddy.domain.User;
import com.garagebuddy.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "garagebuddy.notifications.email", name = "enabled", havingValue = "true", matchIfMissing = false)
public class EmailNotificationSender implements NotificationSender {
    private static final Logger log = LoggerFactory.getLogger(EmailNotificationSender.class);
    private final EmailService emailService;
    private final UserRepository userRepository;

    public EmailNotificationSender(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Override
    public void sendReminder(Reminder reminder) {
        if (reminder == null || reminder.car == null || reminder.car.user == null) return;
        var userOpt = userRepository.findById(reminder.car.user.id);
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            try {
                emailService.sendReminder(u, reminder);
            } catch (Exception ex) {
                log.warn("Failed to send reminder email to {}", u.email);
            }
        }
    }
}
