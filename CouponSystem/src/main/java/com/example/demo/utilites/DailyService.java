package com.example.demo.utilites;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repo.CouponRepo;
import com.example.demo.beans.Coupon;



/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component

public class DailyService {

	
	
	@Autowired
	private CouponRepo coupRepo;
	
	/***
	 * Getting All Expired Coupons
	 * @return
	 * @throws NoCouponsException
	 */
	public void removeExpired()
	{
	
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		List<Coupon> coupons = coupRepo.ExpiredCoupons(date);
		if(coupons.isEmpty() || coupons == null)
			return;
		for (Coupon coupon : coupons) {
			coupRepo.removeCoupon(coupon.getId());
		}
		
	}

}