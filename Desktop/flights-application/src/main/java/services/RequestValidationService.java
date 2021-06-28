package services;

import org.springframework.http.HttpStatus;

import models.FlightException;

public class RequestValidationService {

	public void validateRequestId(Integer id) throws FlightException {
		if(id == null) {
			throw new FlightException(HttpStatus.BAD_REQUEST);
		}
	}
}
