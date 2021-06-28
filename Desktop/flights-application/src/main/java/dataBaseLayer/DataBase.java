package dataBaseLayer;

import java.util.HashMap;

import dataBaseLayer.models.DBItem;
import services.LoggerService;

public class DataBase {
	protected HashMap<String,DBItem> dbData;
	
	public DataBase(HashMap<String, DBItem> data) {
		this.dbData =  data;
	}
	
	public DBItem getDBItem(String id) {
		LoggerService.info("Check if item with id : "+ id + " in data base");
		return this.dbData.get(id);
	}
}