package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.enums.CouponType;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CustomerDAO {
/**
 * 
 * @param customer to create customer in DB
 */
	void createCustomer(Customer customer);
	/**
	 * 
	 * @param customer to remove customer from DB
	 */
	void removeCustomer(Customer customer);
	/**
	 * 
	 * @param customer to update customer in DB
	 */
	void updateCustomer(Customer customer);
	/**
	 * 
	 * @param id to get customer by id
	 * @return Customer
	 */
	Customer getCustomer(int id);
	/**
	 * 
	 * @param customerName to get customer by name
	 * @return Customer
	 */
	Customer getCustomerByName(String customerName);
	/**
	 * 
	 * @return array of all customers
	 */
	ArrayList<Customer> getAllCustomers();
	/**
	 * 
	 * @return array of coupons
	 */
	ArrayList<Coupon> getCoupons();
	/**
	 * 
	 * @param customerName to check customer
	 * @param password to login to customerName
	 * @return true if password correct, and false if incorrect
	 */
	boolean login(String customerName, String password);
	/**
	 * 
	 * @return current customer
	 */
	Customer getLoginCustomer();
	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */
	ArrayList<Coupon> getPurchaseCouponsByType(CouponType type);
	/**
	 * 
	 * @param id to get coupon by id
	 * @return coupon
	 */
	Coupon getPurchasedCoupon(int id);
	/**
	 * 
	 * @param price to get coupons by price
	 * @return array of coupons
	 */
	ArrayList<Coupon> getPurchaseCouponsByPrice(double price);
	
}