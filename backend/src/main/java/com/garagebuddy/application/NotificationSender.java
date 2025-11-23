package com.garagebuddy.application;

import com.garagebuddy.domain.Reminder;

public interface NotificationSender {
    void sendReminder(Reminder reminder);
}
