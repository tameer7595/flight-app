package com.springboot.dosprotectionsystem;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.controllers.WindowController;

import services.CacheService;

@SpringBootApplication
@ComponentScan(basePackageClasses = WindowController.class)
public class DosProtectionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DosProtectionSystemApplication.class, args);
	}

	@Bean
	public CacheService cacheManager() {
		return new CacheService(new HashMap<Integer, List<Long>>(), new HashMap<Integer, List<Long>>());
	}
}
