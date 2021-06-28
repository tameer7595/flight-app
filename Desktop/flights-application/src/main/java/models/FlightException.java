package models;

import org.springframework.http.HttpStatus;

public class FlightException extends Throwable {
	public HttpStatus status;
	public FlightException(HttpStatus status) {
		this.status = status;
	}
}
