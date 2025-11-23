package com.garagebuddy.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_preference")
public class NotificationPreference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    public boolean emailReminders = true;
    public boolean dashboardReminders = true;

    public NotificationPreference() {}
}
