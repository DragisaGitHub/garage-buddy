package com.garagebuddy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);
    boolean existsByUserIdAndVin(Long userId, String vin);
}
