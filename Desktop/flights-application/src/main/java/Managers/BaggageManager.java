package Managers;

import dataBaseLayer.DataBase;
import models.Baggage;
import services.CacheService;
import services.LoggerService;

public class BaggageManager {
	private DataBase baggageDB;
	private CacheService baggageCacheService;

	public BaggageManager(DataBase baggageDB, CacheService baggageCacheService) {
		this.baggageDB = baggageDB;
		this.baggageCacheService = baggageCacheService;
	}

	public boolean baggageCheckIn(int destinationId , String baggageId) {
		boolean checkInSucceeded = this.baggageCacheService.checkIfItemInCache(String.valueOf(destinationId)+baggageId);
		if (checkInSucceeded) {
			LoggerService.info("baggage with Baggage id : " +baggageId +" and Destination id : " + destinationId +" was found in cache");
			return true;
		}
		LoggerService.info("baggage with Baggage id : " +baggageId +" and Destination id : " + destinationId +" not found in cache");
		Baggage BaggageInfo = (Baggage) this.baggageDB.getDBItem(String.valueOf(destinationId)+baggageId);
		if (BaggageInfo == null) {
			LoggerService.info("baggage with Baggage id :" +baggageId +" and Destination id : " + destinationId +" not found in data base");
			return false;
		}
		LoggerService.info("baggage with Baggage id : " +baggageId +" and Destination id : " + destinationId +" was found in data base");
		this.baggageCacheService.cacheItem(BaggageInfo);
		return true;
	}
}
