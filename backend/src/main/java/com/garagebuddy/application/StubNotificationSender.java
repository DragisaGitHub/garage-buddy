package com.garagebuddy.application;

import com.garagebuddy.domain.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StubNotificationSender implements NotificationSender {
    private static final Logger log = LoggerFactory.getLogger(StubNotificationSender.class);

    @Override
    public void sendReminder(Reminder reminder) {
        // placeholder implementation: log minimal info
        log.info("Sending reminder for carId={} due={} type={}", reminder.car != null ? reminder.car.id : null, reminder.dueDate, reminder.type);
    }
}
