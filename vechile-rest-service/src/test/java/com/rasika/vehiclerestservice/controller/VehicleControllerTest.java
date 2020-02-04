package com.rasika.vehiclerestservice.controller;

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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import com.rasika.vehiclerestservice.entity.Vehicle;
import com.rasika.vehiclerestservice.exception.CustomException;
import com.rasika.vehiclerestservice.service.VehicleService;

@RunWith(MockitoJUnitRunner.class)
public class VehicleControllerTest {

	private VehicleController vehicleController;

	@Mock
	private VehicleService vehicleService;

	@Mock
	private Errors errors;

	@Before
	public void initialize() {
		vehicleController = new VehicleController(vehicleService);
	}

	@Test
	public void testGetVehicles() {
		List<Vehicle> expectedVehicles = new ArrayList<>();
		Vehicle vehicle = new Vehicle("Toyota", "Camry", 2018);
		expectedVehicles.add(vehicle);

		when(vehicleService.getVehicles()).thenReturn(expectedVehicles);

		List<Vehicle> actualVehicles = vehicleController.getVehicles(null, null).getBody();

		assertEquals(expectedVehicles, actualVehicles);
	}

	@Test
	public void testGetVehicleById() {
		Vehicle expectedVehicle = new Vehicle("Toyota", "Camry", 2018);

		when(vehicleService.getVehicleById(anyInt())).thenReturn(Optional.of(expectedVehicle));

		Vehicle actualVehicle = vehicleController.getVehicleById(1).getBody();

		assertEquals(expectedVehicle, actualVehicle);
	}

	@Test
	public void testAddVehicle() {
		Vehicle expectedVehicle = new Vehicle("Toyota", "Camry", 2018);
		when(vehicleService.addVehicle(any(Vehicle.class))).thenReturn(expectedVehicle);
		when(errors.hasErrors()).thenReturn(false);

		Vehicle actualVehicle = vehicleController.addVehicle(expectedVehicle, errors).getBody();

		assertEquals(expectedVehicle, actualVehicle);
	}

	@Test
	public void testUpdateVehicle() {
		Vehicle expectedVehicle = new Vehicle("Toyota", "Camry", 2018);

		when(vehicleService.updateVehicle(any(Vehicle.class))).thenReturn(expectedVehicle);
		when(errors.hasErrors()).thenReturn(false);

		Vehicle actualVehicle = vehicleController.updateVehicle(expectedVehicle, errors).getBody();

		assertEquals(expectedVehicle, actualVehicle);
	}

	@Test
	public void testDeleteVehicle() {
		doNothing().when(vehicleService).deleteVehicle(anyInt());

		HttpStatus status = vehicleController.deleteVehicle(1).getStatusCode();
		
		assertEquals(HttpStatus.NO_CONTENT, status);
	}
	
	@Test(expected = CustomException.class)
	public void testUpdateVehicleThrowsException() {
		Vehicle expectedVehicle = new Vehicle("Toyota", "Camry", 2018);

		when(errors.hasErrors()).thenReturn(true);

		vehicleController.updateVehicle(expectedVehicle, errors);
	}
	
	@Test(expected = CustomException.class)
	public void testAddVehicleThrowsException() {
		Vehicle expectedVehicle = new Vehicle("Toyota", "Camry", 2018);

		when(errors.hasErrors()).thenReturn(true);

		vehicleController.addVehicle(expectedVehicle, errors);
	}
	
	@Test(expected = CustomException.class)
	public void testGetVehicleByIdThrowsException() {
		when(vehicleService.getVehicleById(anyInt())).thenReturn(Optional.empty());
		
		vehicleController.getVehicleById(1);
	}
}
