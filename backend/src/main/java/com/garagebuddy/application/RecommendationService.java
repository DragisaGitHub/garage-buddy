package com.garagebuddy.application;

import com.garagebuddy.domain.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {
    public Map<String, Object> recommendForCar(Car car) {
        // placeholder deterministic recommendation logic
        return Map.of("recommendation", "Keep regular maintenance");
    }

    public List<Map<String,Object>> recommendForCars(List<Car> cars) {
        return cars.stream().map(this::recommendForCar).toList();
    }
}
