package Managers;
import java.util.Random;

import dataBaseLayer.DataBase;
import dataBaseLayer.models.DBItem;
import models.Coupon;
import services.CacheService;
import services.LoggerService;

public class CouponManager {
	private  double [] discount = {0.1, 0.5, 0.6};
	private DataBase CouponDB;
	private CacheService CouponCacheService;
	
	public CouponManager(DataBase CouponDB, CacheService CouponCacheService) {
		this.CouponDB = CouponDB;
		this.CouponCacheService = CouponCacheService;
	}
	public double checkCoupon(int couponId,double p) {
		Coupon CouponItem = (Coupon) this.CouponCacheService.getCacheItem(String.valueOf(couponId) + String.valueOf(p));
        Random rand = new Random();
        int randomDiscount = rand.nextInt(1000);
        double discountVlue = discount[randomDiscount % 3 ];
		if (CouponItem != null) {
			LoggerService.info("Coupon with Coupon id : " + couponId + " was found in cache");
			LoggerService.info("Coupon with Coupon id : " + couponId + " get " + discountVlue*CouponItem.price + " discount");
			return discountVlue*CouponItem.price;
		}
		LoggerService.info("Coupon with Coupon id : " + couponId + " not found in cache");
		Coupon couponInfo = (Coupon) this.CouponDB.getDBItem(String.valueOf(couponId)+String.valueOf(p));
		if (couponInfo == null) {
			LoggerService.info("Coupon with Coupon id : " + couponId + " not found in data base");
			LoggerService.info("Coupon with Coupon id : " + couponId + " with no discount");
			return p;
		}
		LoggerService.info("Coupon with Coupon id : " + couponId + " was found in data base");
		LoggerService.info("Coupon with Coupon id : " + couponId + " get" + discountVlue*couponInfo.price + " discount");
		
		this.CouponCacheService.cacheItem(couponInfo);
		return discountVlue*couponInfo.price;
	}
}
