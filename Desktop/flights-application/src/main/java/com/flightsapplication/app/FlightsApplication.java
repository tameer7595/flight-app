package com.flightsapplication.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import services.BootStrapper;
import services.LoggerService;

@SpringBootApplication
public class FlightsApplication {

	public static void main(String[] args) {
		LoggerService.info("Initialize data source");
		BootStrapper.init();
		LoggerService.info("Run flight application");
		SpringApplication.run(FlightsApplication.class, args);
	}

}
