package com.garagebuddy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByDueDateLessThanEqualAndSentFalse(java.time.LocalDate date);
}
