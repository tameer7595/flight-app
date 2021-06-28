package models;

import dataBaseLayer.models.DBItem;

public class Coupon extends DBItem  {
	public int couponId;
	public double price;
	public Coupon(int couponId, double price) {
		this.couponId = couponId;
		this.price = price;
		this.id= String.valueOf(couponId) + String.valueOf(price) ;
	}
}
