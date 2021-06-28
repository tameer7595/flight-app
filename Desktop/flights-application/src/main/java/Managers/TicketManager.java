package Managers;

import dataBaseLayer.DataBase;
import models.Ticket;
import services.CacheService;
import services.LoggerService;

public class TicketManager {
	private DataBase ticketDB;
	private CacheService ticketCacheService;

	public TicketManager(DataBase ticketDB, CacheService ticketCacheService) {
		this.ticketDB = ticketDB;
		this.ticketCacheService = ticketCacheService;
	}

	public boolean checkTicket(int ticketId) {
		boolean isTicketExist = this.ticketCacheService.checkIfItemInCache(String.valueOf(ticketId));
		if (isTicketExist) {
			LoggerService.info("Ticket with  id : " +  ticketId + " was found in cache");
			return true;
		}
		LoggerService.info("Ticket with  id : " +  ticketId + " not found in cache");
		Ticket ticket = (Ticket) this.ticketDB.getDBItem(String.valueOf(ticketId));
		if (ticket == null) {
			LoggerService.info("Ticket with  id : " +  ticketId + " not found in data base");
			return false;
		}
		LoggerService.info("Ticket with  id : " +  ticketId + " was found in data base ");
		this.ticketCacheService.cacheItem(ticket);
		return true;
	}
}
