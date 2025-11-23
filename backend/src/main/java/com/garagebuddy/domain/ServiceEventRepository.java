package com.garagebuddy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceEventRepository extends JpaRepository<ServiceEvent, Long> {
    List<ServiceEvent> findByCarIdOrderByDateDesc(Long carId);
}
