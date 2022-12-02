package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import services.CacheService;

@RestController
public class WindowController {
	private static final Logger logger = LoggerFactory.getLogger(WindowController.class);
	
	@Autowired
	private  CacheService cacheService;

	
	@GetMapping("/StaticWindow")
	public synchronized  ResponseEntity<String>  getStaticWindow(@RequestParam("clientId") int clientId){
		logger.info("going to add static window for client id :  {}",clientId);
		this.cacheService.checkStaticTimeFrame(clientId);
		List<Long> staticsWindowForClient = this.cacheService.getStaticWindows(clientId);
		if(staticsWindowForClient.size() == 5) {
			logger.error("client id {} got 5 static windows in the last 5 seconds", clientId);
			return new ResponseEntity<String>("bad",HttpStatus.SERVICE_UNAVAILABLE);
		}else {
			this.cacheService.setStaticWindow(clientId);
			logger.info("adding static window for client id {} finished successfully", clientId);
			return new ResponseEntity<String>("Ok",HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/DynamicWindow")
	public synchronized  ResponseEntity<String> getDynamicWindow(@RequestParam("clientId") int clientId){
		logger.info("going to add dynamic window for client id :  {}",clientId);
		List<Long> dynamicsWindowForClient = this.cacheService.getDynamicWindows(clientId);
		if(dynamicsWindowForClient.size() < 5) {
			this.cacheService.setDynamicWindow(clientId);
			logger.info("adding dynamic window for client id {} finished successfully", clientId);
			return new ResponseEntity<String>("Ok",HttpStatus.OK);
		}else {
		logger.error("client id {} got 5 dynamic windows in the last 5 seconds", clientId);
		return new ResponseEntity<String>("bad",HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
}
