package com.garagebuddy.application;

import com.garagebuddy.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DashboardService {
    private final CarRepository carRepository;
    private final ServiceEventRepository serviceEventRepository;

    public DashboardService(CarRepository carRepository, ServiceEventRepository serviceEventRepository) {
        this.carRepository = carRepository;
        this.serviceEventRepository = serviceEventRepository;
    }

    public Map<String, Object> getDashboardForUser(Long userId) {
        List<Car> cars = carRepository.findByUserId(userId);
        List<Map<String, Object>> carDtos = new ArrayList<>();
        for (Car c : cars) {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", c.id);
            dto.put("brand", c.brand);
            dto.put("model", c.model);
            dto.put("vin", c.vin);
            dto.put("health", "GREEN"); // placeholder
            dto.put("metrics", Collections.emptyMap());
            carDtos.add(dto);
        }
        Map<String,Object> out = new HashMap<>();
        out.put("cars", carDtos);
        return out;
    }
}
