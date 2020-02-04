package com.rasika.vehiclerestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasika.vehiclerestservice.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	List<Vehicle> findByMakeAndModel(String make, String model);
}
