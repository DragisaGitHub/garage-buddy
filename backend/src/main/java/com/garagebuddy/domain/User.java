package com.garagebuddy.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(name = "password_hash", nullable = false)
    public String passwordHash;

    public String name;

    @Column(name = "created_at")
    public Instant createdAt;

    @Column(name = "deleted_at")
    public Instant deletedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Car> cars = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }

    @PreRemove
    public void preRemove() {
        // Constitution compliance: cascade delete all user data
        // Cars and related service events/reminders are automatically deleted via orphanRemoval
        deletedAt = Instant.now();
    }

    public User() {}
}
