package com.garagebuddy.application;

import com.garagebuddy.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEventService {
    private final ServiceEventRepository repo;
    private final CarRepository carRepo;

    public ServiceEventService(ServiceEventRepository repo, CarRepository carRepo) {
        this.repo = repo;
        this.carRepo = carRepo;
    }

    public ServiceEvent addEvent(Long carId, ServiceEvent ev) {
        Car car = carRepo.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found"));
        ev.car = car;
        return repo.save(ev);
    }

    public List<ServiceEvent> listForCar(Long carId) {
        return repo.findByCarIdOrderByDateDesc(carId);
    }
}
