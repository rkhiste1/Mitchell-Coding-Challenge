package com.rasika.vehiclerestservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rasika.vehiclerestservice.entity.Vehicle;
import com.rasika.vehiclerestservice.repository.VehicleRepository;

@SpringBootApplication
public class VehicleRestServiceApplication implements CommandLineRunner {

	private VehicleRepository vehicleRepository;

	public VehicleRestServiceApplication(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(VehicleRestServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		vehicleRepository.save(new Vehicle("Toyota", "Camry", 2018));
	}

}
