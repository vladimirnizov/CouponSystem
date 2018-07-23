package com.example.demo.facades;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.dao.CouponDBDAO;
import com.example.demo.dao.CustomerDBDAO;
import com.example.demo.enums.ClientType;
import com.example.demo.enums.CouponType;
import com.example.demo.exceptions.AlreadyPurchased;
import com.example.demo.exceptions.OutOfStock;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component
public class CustomerFacade implements CouponClientFacade{

	@Autowired
	CustomerDBDAO customerDBDAO;
	
	@Autowired
	CouponDBDAO couponDBDAO;
	
	
	
	public CustomerFacade() {}
	
	/**
	 * 
	 * @param userName 
	 * @param password 
	 * @return CouponClientFacade
	 */
	@Override
	public CustomerFacade login(String userName, String password, ClientType type) {
		
		if(!type.equals(ClientType.CUSTOMER))
		{
			System.out.println(type+ " is wrong type.");
			return null;
		}
		
		if(customerDBDAO.login(userName, password))
			return this;
		return null;
	}

	/**
	 * 
	 * @return array list of coupons
	 */
	public ArrayList<Coupon> getAllCoupons() 
	{
		return couponDBDAO.getCoupons();
	}
	
	/**
	 * 
	 * @return array list of coupons
	 */
	public ArrayList<Coupon> getPurchasedCoupons() 
	{
		return customerDBDAO.getCoupons();
	}

	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */
	public ArrayList<Coupon> getPurchaseCouponsByType(CouponType type) 
	{
		return customerDBDAO.getPurchaseCouponsByType(type);
	}

	/**
	 * 
	 * @param price to get coupons by price
	 * @return array of coupons
	 */
	public ArrayList<Coupon> getPurchaseCouponsByPrice(double price) 
	{
		return customerDBDAO.getPurchaseCouponsByPrice(price);
	}

	/**
	 * 
	 * @param id to get coupon by id
	 * @return Coupon
	 */
	public Coupon getCoupon(int id) 
	{
		return couponDBDAO.getCoupon(id);
	}
	/**
	 * 
	 * @param coupon to purchase Coupon
	 * @throws AlredyPurchased exception
	 */
	public synchronized void  purchaseCoupon(Coupon coupon) {

		if(coupon.getAmount() == 0){
			throw new OutOfStock(coupon.getTitle() + " is out of stock");
		}else if(customerDBDAO.getPurchasedCoupon(coupon.getId())!=null){
			throw new AlreadyPurchased(coupon.getTitle() +" already purchased");
		}else {
			Customer currentCustomer = customerDBDAO.getLoginCustomer();
			Coupon currentCoupon = this.getCoupon(coupon.getId());
			currentCustomer.addCoupon(currentCoupon);
			currentCoupon.setAmount(currentCoupon.getAmount()-1);
			couponDBDAO.updateCoupon(currentCoupon);
			customerDBDAO.updateCustomer(currentCustomer);
		}
	}

	
	/**
	 * 

	 * @return Customer
	 */
	public Customer getCust() 
	{
		return customerDBDAO.getLoginCustomer();
	}

}