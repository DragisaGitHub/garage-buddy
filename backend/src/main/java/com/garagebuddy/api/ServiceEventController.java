package com.garagebuddy.api;

import com.garagebuddy.application.ServiceEventService;
import com.garagebuddy.domain.CarRepository;
import com.garagebuddy.domain.ServiceEvent;
import com.garagebuddy.domain.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars/{carId}/service-events")
@PreAuthorize("isAuthenticated()")
public class ServiceEventController {
    private final ServiceEventService service;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public ServiceEventController(ServiceEventService service, CarRepository carRepository, UserRepository userRepository) {
        this.service = service; this.carRepository = carRepository; this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ServiceEvent> add(Authentication authentication, @PathVariable Long carId, @RequestBody ServiceEvent ev) {
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow();
        var car = carRepository.findById(carId).orElseThrow();
        if (!car.user.id.equals(user.id)) throw new IllegalArgumentException("Not owner");
        return ResponseEntity.ok(service.addEvent(carId, ev));
    }

    @GetMapping
    public ResponseEntity<List<ServiceEvent>> list(Authentication authentication, @PathVariable Long carId) {
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow();
        var car = carRepository.findById(carId).orElseThrow();
        if (!car.user.id.equals(user.id)) throw new IllegalArgumentException("Not owner");
        return ResponseEntity.ok(service.listForCar(carId));
    }
}
