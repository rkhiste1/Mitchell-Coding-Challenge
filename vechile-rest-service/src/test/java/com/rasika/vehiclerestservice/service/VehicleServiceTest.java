package com.rasika.vehiclerestservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rasika.vehiclerestservice.entity.Vehicle;
import com.rasika.vehiclerestservice.repository.VehicleRepository;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

	private VehicleService vehicleService;
	
	@Mock
	private VehicleRepository vehicleRepository;

	@Before
	public void initialize() {
		vehicleService = new VehicleServiceImpl(vehicleRepository);
	}

	@Test
	public void testGetVehicles() {
		List<Vehicle> expectedVehicles = new ArrayList<>();
		Vehicle vehicle = new Vehicle("Toyota", "Camry", 2018);
		expectedVehicles.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(expectedVehicles);

		List<Vehicle> actualVehicles = vehicleService.getVehicles();

		assertEquals(expectedVehicles, actualVehicles);
	}

	@Test
	public void testGetVehicleById() {
		Vehicle vehicle = new Vehicle("Toyota", "Camry", 2018);
		Optional<Vehicle> expctedVehicle = Optional.of(vehicle);
		
		when(vehicleRepository.findById(anyInt())).thenReturn(expctedVehicle);

		Optional<Vehicle> actualVehicle = vehicleService.getVehicleById(vehicle.getId());

		assertEquals(expctedVehicle, actualVehicle);
	}

	@Test
	public void testAddVehicle() {
		Vehicle expctedVehicle = new Vehicle("Toyota", "Camry", 2018);

		when(vehicleRepository.save(any(Vehicle.class))).thenReturn(expctedVehicle);

		Vehicle actualVehicle = vehicleService.addVehicle(expctedVehicle);

		assertEquals(expctedVehicle, actualVehicle);
	}

	@Test
	public void testUpdateVehicle() {
		Vehicle expctedVehicle = new Vehicle("Toyota", "Camry", 2018);

		when(vehicleRepository.save(any(Vehicle.class))).thenReturn(expctedVehicle);
		when(vehicleRepository.findById(anyInt())).thenReturn(Optional.of(expctedVehicle));
		
		Vehicle actualVehicle = vehicleService.updateVehicle(expctedVehicle);

		assertEquals(expctedVehicle, actualVehicle);
	}

	@Test
	public void testDeleteVehicle() {
		Vehicle expctedVehicle = new Vehicle("Toyota", "Camry", 2018);
		
		when(vehicleRepository.findById(anyInt())).thenReturn(Optional.of(expctedVehicle));
		doNothing().when(vehicleRepository).deleteById(anyInt());

		vehicleService.deleteVehicle(1);

		Mockito.verify(vehicleRepository, Mockito.times(1)).deleteById(anyInt());
	}
}
