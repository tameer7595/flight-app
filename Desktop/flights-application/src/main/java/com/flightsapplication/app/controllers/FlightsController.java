package com.flightsapplication.app.controllers;

import models.FlightException;
import models.Ticket;
import services.BootStrapper;
import services.LoggerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class FlightsController {

	@GetMapping("flights/tickets")
	public ResponseEntity<Boolean> checkTicket(@RequestParam int ticketId) {
		try {
			LoggerService.info("Calling check ticket API");
			boolean isTicketExists = BootStrapper.ticketManager.checkTicket(ticketId);

			if (isTicketExists) {
				LoggerService.info("Ticket was found for ticket id: " + ticketId);
				return ResponseEntity.status(HttpStatus.OK).body(true);
			} else {
				LoggerService.error("Ticket not found for ticket id: " + ticketId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
			}
		} catch (Exception exception) {
			LoggerService.error("Internal server error, exception message: " + exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}

	}

	@GetMapping(path = "flights/baggage")
	public ResponseEntity<Boolean> checkBaggage(@RequestParam int distinationId, @RequestParam String baggageId) {
		try {
			LoggerService.info("Calling baggage check in  API");
			boolean isBaggageCheckInSucceeded = BootStrapper.baggageManager.baggageCheckIn(distinationId, baggageId);
			if (isBaggageCheckInSucceeded) {
				LoggerService.info("Baggage Check in  Succeeded  for baggage id: " + baggageId);
				return ResponseEntity.status(HttpStatus.OK).body(true);
			} else {
				LoggerService.error("Baggage Check in  failed  for baggage id: " + baggageId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
			}
		} catch (Exception exception) {
			LoggerService.error("Internal server error, exception message: " + exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

	@GetMapping(path = "flights/coupon")
	public ResponseEntity<String> checkCoupon(@RequestParam int couponId, @RequestParam double price) {
		try {
			LoggerService.info("Calling Coupon check in  API");
			double couponFinalPrice = BootStrapper.couponManager.checkCoupon(couponId,price);
			if (couponFinalPrice == price) {
				LoggerService.info("InValid coupon for Coupon id: " + couponId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid coupon with id " + couponId +" with final price : "+couponFinalPrice);
			} else {
				LoggerService.error("valid coupon for Coupon id:: " + couponId);
				return ResponseEntity.status(HttpStatus.OK).body("Valid coupon with id " + couponId +" with final price : "+couponFinalPrice);
			}
		} catch (Exception exception) {
			LoggerService.error("Internal server error, exception message: " + exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}
}
