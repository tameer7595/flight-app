package models;

import dataBaseLayer.models.DBItem;

public class Ticket extends DBItem {
	public Ticket(int id) {
		this.id = String.valueOf(id);
	}
}
