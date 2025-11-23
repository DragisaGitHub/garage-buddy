package com.garagebuddy.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reminder")
public class Reminder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    public Car car;

    public String type;
    public LocalDate dueDate;
    public boolean sent;

    public Reminder() {}
}
