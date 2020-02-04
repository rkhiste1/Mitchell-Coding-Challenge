package com.rasika.vehiclerestservice.service;

import java.util.List;
import java.util.Optional;

import com.rasika.vehiclerestservice.entity.Vehicle;

public interface VehicleService {

	List<Vehicle> getVehicles();

	Optional<Vehicle> getVehicleById(int id);

	Vehicle addVehicle(Vehicle vehicle);

	Vehicle updateVehicle(Vehicle vehicle);

	void deleteVehicle(int id);

}
