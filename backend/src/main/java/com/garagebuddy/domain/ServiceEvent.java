package com.garagebuddy.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "service_events")
public class ServiceEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    public Car car;

    public LocalDate date;
    public int mileage;
    public String description;
    public String type;
    public BigDecimal cost;

    public ServiceEvent() {}
}
