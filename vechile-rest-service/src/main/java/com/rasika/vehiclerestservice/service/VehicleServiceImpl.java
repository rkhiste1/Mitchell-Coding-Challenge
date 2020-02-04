package com.rasika.vehiclerestservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rasika.vehiclerestservice.entity.Vehicle;
import com.rasika.vehiclerestservice.exception.CustomException;
import com.rasika.vehiclerestservice.repository.VehicleRepository;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public List<Vehicle> getVehicles() {
		return vehicleRepository.findAll();
	}

	@Override
	public Optional<Vehicle> getVehicleById(int id) {
		return vehicleRepository.findById(id);
	}

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		Optional<Vehicle> optionalVehicle = getVehicleById(vehicle.getId());
		if (!optionalVehicle.isPresent()) {
			throw new CustomException("Vehicle does not exist", HttpStatus.NOT_FOUND);
		}
		return vehicleRepository.save(vehicle);
	}

	@Override
	public void deleteVehicle(int id) {
		Optional<Vehicle> optionalVehicle = getVehicleById(id);
		if (!optionalVehicle.isPresent()) {
			throw new CustomException("Vehicle does not exist", HttpStatus.NOT_FOUND);
		}
		vehicleRepository.deleteById(id);
	}
}
