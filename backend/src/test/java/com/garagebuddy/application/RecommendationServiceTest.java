package com.garagebuddy.application;

import com.garagebuddy.domain.Car;
import com.garagebuddy.domain.ServiceEvent;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationServiceTest {
    
    @Test
    void shouldRecommendForOldCar() {
        RecommendationService svc = new RecommendationService();
        Car car = new Car();
        car.id = 1L;
        car.year = 2000;
        car.mileage = 50000;
        car.serviceEvents = new ArrayList<>();
        
        Map<String, Object> result = svc.recommendForCar(car);
        assertNotNull(result);
        List<String> recs = (List<String>) result.get("recommendations");
        assertTrue(recs.stream().anyMatch(r -> r.contains("older vehicle")));
    }
    
    @Test
    void shouldRecommendForHighMileageCar() {
        RecommendationService svc = new RecommendationService();
        Car car = new Car();
        car.id = 2L;
        car.year = 2020;
        car.mileage = 150000;
        car.serviceEvents = new ArrayList<>();
        
        Map<String, Object> result = svc.recommendForCar(car);
        List<String> recs = (List<String>) result.get("recommendations");
        assertTrue(recs.stream().anyMatch(r -> r.contains("High mileage")));
    }
    
    @Test
    void shouldRecommendForHighMaintenanceCost() {
        RecommendationService svc = new RecommendationService();
        Car car = new Car();
        car.id = 3L;
        car.year = 2020;
        car.mileage = 50000;
        car.serviceEvents = new ArrayList<>();
        
        ServiceEvent ev = new ServiceEvent();
        ev.cost = BigDecimal.valueOf(6000);
        ev.date = LocalDate.now().minusMonths(2);
        car.serviceEvents.add(ev);
        
        Map<String, Object> result = svc.recommendForCar(car);
        List<String> recs = (List<String>) result.get("recommendations");
        assertTrue(recs.stream().anyMatch(r -> r.contains("High maintenance costs")));
    }
}
