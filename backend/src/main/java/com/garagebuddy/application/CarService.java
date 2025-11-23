package com.garagebuddy.application;

import com.garagebuddy.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<Car> listUserCars(Long userId) {
        return carRepository.findByUserId(userId);
    }

    @Transactional
    public Car addCar(Long userId, Car car) {
        if (carRepository.existsByUserIdAndVin(userId, car.vin)) {
            throw new IllegalArgumentException("Duplicate VIN for this user");
        }
        User u = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        car.user = u;
        return carRepository.save(car);
    }
}
