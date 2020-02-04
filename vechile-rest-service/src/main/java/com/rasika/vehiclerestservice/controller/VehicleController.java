package com.rasika.vehiclerestservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasika.vehiclerestservice.entity.Vehicle;
import com.rasika.vehiclerestservice.exception.CustomException;
import com.rasika.vehiclerestservice.service.VehicleService;

@CrossOrigin
@RestController
@RequestMapping("/vehicleservice")
public class VehicleController {

	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@GetMapping("/vehicles")
	public ResponseEntity<List<Vehicle>> getVehicles(
			@RequestParam(value = "make", required = false) String make,
			@RequestParam(value = "model", required = false) String model) {

		List<Vehicle> vehicles = vehicleService.getVehicles();
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}

	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") int id) {
		Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
		if (!vehicle.isPresent()) {
			throw new CustomException("Vehicle not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(vehicle.get(), HttpStatus.OK);
	}

	@PostMapping(value = "/vehicles")
	public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle, Errors errors) {
		if (errors.hasErrors()) {
			throw new CustomException(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Vehicle>(vehicleService.addVehicle(vehicle), HttpStatus.CREATED);
	}

	@PutMapping(value = "/vehicles")
	public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle, Errors errors) {
		if (errors.hasErrors()) {
			throw new CustomException(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicle), HttpStatus.OK);
	}

	@DeleteMapping(value = "/vehicles/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable("id") int id) {
		vehicleService.deleteVehicle(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
