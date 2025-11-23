package com.garagebuddy.application;

import com.garagebuddy.domain.Car;
import com.garagebuddy.domain.ServiceEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {
    
    public Map<String, Object> recommendForCar(Car car) {
        List<String> recommendations = new ArrayList<>();
        int age = LocalDate.now().getYear() - car.year;
        
        // Age-based recommendation
        if (age > 10) {
            recommendations.add("Consider comprehensive inspection for older vehicle");
        }
        
        // Mileage-based
        if (car.mileage > 100000) {
            recommendations.add("High mileage vehicle - check transmission and engine carefully");
        }
        
        // Cost-based (if service events present)
        if (car.serviceEvents != null && !car.serviceEvents.isEmpty()) {
            BigDecimal totalCost = car.serviceEvents.stream()
                .map(se -> se.cost == null ? BigDecimal.ZERO : se.cost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            if (totalCost.compareTo(BigDecimal.valueOf(5000)) > 0) {
                recommendations.add("High maintenance costs detected - evaluate if vehicle is worth keeping");
            }
            
            // Check last service date
            ServiceEvent lastService = car.serviceEvents.stream()
                .filter(se -> se.date != null)
                .max((a, b) -> a.date.compareTo(b.date))
                .orElse(null);
            
            if (lastService != null && lastService.date.isBefore(LocalDate.now().minusMonths(6))) {
                recommendations.add("No service in 6+ months - schedule maintenance checkup");
            }
        } else {
            recommendations.add("No service history recorded - add past maintenance events");
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("carId", car.id);
        result.put("recommendations", recommendations);
        result.put("priority", recommendations.isEmpty() ? "LOW" : "MEDIUM");
        return result;
    }

    public List<Map<String,Object>> recommendForCars(List<Car> cars) {
        return cars.stream().map(this::recommendForCar).toList();
    }
}
