package com.garagebuddy.domain;

import jakarta.persistence.*;
import java.time.Instant;

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

    public User() {}
}
