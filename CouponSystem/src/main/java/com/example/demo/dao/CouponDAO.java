package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.beans.Coupon;
import com.example.demo.enums.CouponType;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CouponDAO {
/**
 * 
 * @param coup to create Coupon in DB
 */
	void createCoupon(Coupon coup);
	/**
	 * 
	 * @param coup to remove Coupon from DB
	 */
	void removeCoupon(Coupon coup);
	/**
	 * 
	 * @param coup to update coupon in DB
	 */
	void updateCoupon(Coupon coup);
	/**
	 * 
	 * @param id to get coupon by id
	 * @return Coupon
	 */
	Coupon getCoupon(int id);
	/**
	 * 
	 * @return array of all coupons
	 */
	ArrayList<Coupon> getCoupons();
	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */ 
	ArrayList<Coupon> getCouponsByType(CouponType type);
	
}