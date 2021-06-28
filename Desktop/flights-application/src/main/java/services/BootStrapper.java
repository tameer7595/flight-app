package services;

import dataBaseLayer.DataBase;
import dataBaseLayer.models.DBItem;
import models.Baggage;
import models.Coupon;
import models.Ticket;

import java.util.HashMap;

import Managers.BaggageManager;
import Managers.CouponManager;
import Managers.TicketManager;

public class BootStrapper {

	private static HashMap<String, DBItem> initTicketData = new HashMap<String, DBItem>() {
		{
			this.put("1", new Ticket(1));
			this.put("2", new Ticket(2));
			this.put("3", new Ticket(3));
			this.put("4", new Ticket(4));
		}
	};
	private static HashMap<String, DBItem> initBaggageData = new HashMap<String, DBItem>() {
		{
			this.put("1b1", new Baggage(1,"b1"));
			this.put("2b2", new Baggage(2,"b2"));
			this.put("3b3", new Baggage(3,"b3"));
			this.put("4b4", new Baggage(4,"b4"));
		}
	};
	private static HashMap<String, DBItem> initCouponsData = new HashMap<String, DBItem>() {
		{
			this.put("11300.4", new Coupon(11,300.4));
			this.put("12400.5", new Coupon(12,400.5));
			this.put("13120.0", new Coupon(13,120));
			this.put("14500.0", new Coupon(14,500));
		}
	};

	public static RequestValidationService requestValidationService;
	
	public static TicketManager ticketManager;
	public static BaggageManager baggageManager;
	public static CouponManager couponManager;


	public static void init() {
		requestValidationService = new RequestValidationService();
		initTicketManager();
		initBaggageManager();
		initCouponManager();
	}

	private static void initTicketManager() {
		DataBase ticketDB = new DataBase(initTicketData);
		CacheService ticketCacheService = new CacheService();
		ticketManager = new TicketManager(ticketDB, ticketCacheService);
	}
	private static void initBaggageManager() {
		DataBase BaggageDB = new DataBase(initBaggageData);
		CacheService BaggageCacheService = new CacheService();
		baggageManager = new BaggageManager(BaggageDB, BaggageCacheService);
	}
	private static void initCouponManager() {
		DataBase CouponDB = new DataBase(initCouponsData);
		CacheService CouponCacheService = new CacheService();
		couponManager = new CouponManager(CouponDB, CouponCacheService);
	}
}
