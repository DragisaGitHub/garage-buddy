package com.garagebuddy.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "vin"}))
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    public String brand;
    public String model;
    public int year;
    public int mileage;
    public String vin;
    public LocalDate registrationExpiry;
    public BigDecimal purchasePrice;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ServiceEvent> serviceEvents = new ArrayList<>();

    public Car() {}
}
