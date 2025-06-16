package com.newVehicleRental.newVehicleRental.service;

import com.newVehicleRental.newVehicleRental.model.vehicle;
import com.newVehicleRental.newVehicleRental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailableTrue();
    }

    public Optional<vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public vehicle createVehicle(vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean bookVehicle(Long id) {
        Optional<vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isPresent()) {
            vehicle vehicle = vehicleOpt.get();
            if (!vehicle.isAvailable()) {
                return false;
            }
            vehicle.setAvailable(false);
            vehicleRepository.save(vehicle);
            return true;
        }
        return false;
    }

    public void updateAvailability(Long id, boolean available) {
        Optional<vehicle> vehicleOpt = vehicleRepository.findById(id);
        vehicleOpt.ifPresent(v -> {
            v.setAvailable(available);
            vehicleRepository.save(v);
        });
    }
}
