package com.garagebuddy.application;

import com.garagebuddy.domain.Reminder;
import com.garagebuddy.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final String fromAddress;

    public EmailService(JavaMailSender mailSender, @Value("${garagebuddy.mail.from:no-reply@garagebuddy.local}") String fromAddress) {
        this.mailSender = mailSender;
        this.fromAddress = fromAddress;
    }

    public void sendReminder(User user, Reminder reminder) {
        if (user == null || user.email == null) return;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo(user.email);
        msg.setSubject("GarageBuddy Reminder: " + (reminder.type == null ? "Maintenance" : reminder.type));
        msg.setText("Hi " + (user.name == null ? "there" : user.name) + ",\n\nThis is a reminder for your vehicle: " +
                (reminder.car != null ? (reminder.car.brand + " " + reminder.car.model) : "your vehicle") +
                "\nDue: " + reminder.dueDate + "\n\n-- GarageBuddy");
        try {
            mailSender.send(msg);
        } catch (Exception ex) {
            // swallow - mail server may not be configured for dev
        }
    }
}
