package models;

import dataBaseLayer.models.DBItem;

public class Baggage extends DBItem {
	public String BaggageId;
	public int Destination;

	public Baggage(int destinationId, String baggageId) {
		this.id = destinationId + baggageId;
		this.BaggageId = baggageId;
		this.Destination = destinationId;
	}
}
