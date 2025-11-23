package com.garagebuddy.api;

import com.garagebuddy.application.CarService;
import com.garagebuddy.domain.Car;
import com.garagebuddy.domain.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;
    private final UserRepository userRepository;

    public CarController(CarService carService, UserRepository userRepository) { this.carService = carService; this.userRepository = userRepository; }

    @GetMapping
    public ResponseEntity<List<Car>> listCars(Authentication authentication) {
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(carService.listUserCars(user.id));
    }

    @PostMapping
    public ResponseEntity<Car> addCar(Authentication authentication, @RequestBody Car car) {
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow();
        Car created = carService.addCar(user.id, car);
        return ResponseEntity.ok(created);
    }
}
