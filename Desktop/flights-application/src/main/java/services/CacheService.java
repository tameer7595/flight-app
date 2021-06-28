package services;

import java.util.HashMap;

import dataBaseLayer.models.DBItem;

public class CacheService {
	protected HashMap<String, DBItem> cacheData;

	public CacheService() {
		this.cacheData = new HashMap<String, DBItem>();
	}

	public boolean checkIfItemInCache(String id) {
		LoggerService.info("Check if item with id :" + id +" in cache");
		return this.cacheData.containsKey(id);
	}

	public void cacheItem(DBItem item) {
		LoggerService.info("cache item with id :" + item.id);
		this.cacheData.put(item.id, item);
	}
	public DBItem getCacheItem(String id) {
		LoggerService.info("get item with id :" + id +" from cache if it exists");
		return this.cacheData.get(id);
	}
}
