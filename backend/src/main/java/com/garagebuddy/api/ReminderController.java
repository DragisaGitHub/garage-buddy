package com.garagebuddy.api;

import com.garagebuddy.application.ReminderService;
import com.garagebuddy.domain.Reminder;
import com.garagebuddy.domain.User;
import com.garagebuddy.domain.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderService reminderService;
    private final UserRepository userRepository;

    public ReminderController(ReminderService reminderService, UserRepository userRepository) {
        this.reminderService = reminderService;
        this.userRepository = userRepository;
    }

    @GetMapping("/due")
    public ResponseEntity<List<Reminder>> due(Authentication authentication, @RequestParam(required = false) String date) {
        LocalDate d = (date == null) ? LocalDate.now() : LocalDate.parse(date);
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        List<Reminder> all = reminderService.findDueReminders(d);
        List<Reminder> filtered = all.stream()
                .filter(r -> r.car != null && r.car.user != null && r.car.user.id.equals(user.id))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }
}
