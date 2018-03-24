package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.enums.CouponType;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CompanyDAO {
	/**
	 * 
	 * @param comp to create Company in DB
	 */
	void createCompany(Company comp);
	/**
	 * 
	 * @param comp to remove Company from DB
	 */
	void removeCompany(Company comp);
	/**
	 * 
	 * @param comp to update Company in DB
	 */
	void updateCompany(Company comp);
	/**
	 * 
	 * @param id to get Company by id
	 * @return Company
	 */
	Company getCompany(int id);
	/**
	 * 
	 * @return array of all Companies
	 */
	ArrayList<Company> getAllCompanies();
	
	/**
	 * 
	 * @return array of all coupons
	 */
	ArrayList<Coupon> getCoupons();
	/**
	 * 
	 * @param compName to check company by name
	 * @param password to login to compName
	 * @return true if password correct, and false if incorrect
	 */
	boolean login(String compName, String password);
	
	/**
	 * 
	 * @param compName to get company by name
	 * @return Company with name compName
	 */
	Company getCompanyByName(String compName);
	/**
	 * 
	 * @return the current company
	 */
	Company loggedCompany();
	/**
	 * 
	 * @param title to get coupon by title
	 * @return Coupon
	 */
	Coupon getCouponByTitle(String title);
	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */
	ArrayList<Coupon> getCouponsByType(CouponType type);
	/**
	 * 
	 * @param price to get coupons by price
	 * @return array of coupons
	 */
	ArrayList<Coupon> getCouponsByPrice(double price);
	
	
	
}