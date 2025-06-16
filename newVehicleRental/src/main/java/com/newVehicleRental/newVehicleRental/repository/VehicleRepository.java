package com.newVehicleRental.newVehicleRental.repository;

import com.newVehicleRental.newVehicleRental.model.vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<vehicle, Long> {
    List<vehicle> findByAvailableTrue();
}
