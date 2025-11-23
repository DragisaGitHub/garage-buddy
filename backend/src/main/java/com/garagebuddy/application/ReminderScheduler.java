package com.garagebuddy.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReminderScheduler {
    private static final Logger log = LoggerFactory.getLogger(ReminderScheduler.class);
    private final ReminderService reminderService;
    private final NotificationSender notificationSender;

    public ReminderScheduler(ReminderService reminderService, NotificationSender notificationSender) {
        this.reminderService = reminderService;
        this.notificationSender = notificationSender;
    }

    @Scheduled(cron = "0 0 7 * * ?")
    public void runDaily() {
        var due = reminderService.findDueReminders(LocalDate.now());
        for (var r : due) {
            // placeholder: send notification
            notificationSender.sendReminder(r);
            reminderService.markSent(r);
        }
    }
}
