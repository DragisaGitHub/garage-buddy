package com.garagebuddy.application;

import com.garagebuddy.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderService {
    private final ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public List<Reminder> findDueReminders(LocalDate date) {
        return reminderRepository.findByDueDateLessThanEqualAndSentFalse(date);
    }

    public void markSent(Reminder r) {
        r.sent = true;
        reminderRepository.save(r);
    }
}
