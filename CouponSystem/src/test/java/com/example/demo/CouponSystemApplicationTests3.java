package com.example.demo;


import java.util.ArrayList;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Repo.CompanyRepo;
import com.example.demo.Repo.CouponRepo;
import com.example.demo.Repo.CustomerRepo;
import com.example.demo.beans.Coupon;
import com.example.demo.enums.ClientType;
import com.example.demo.enums.CouponType;
import com.example.demo.facades.AdminFacade;
import com.example.demo.facades.CompanyFacade;
import com.example.demo.facades.CustomerFacade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests3 {


	@Autowired
	public AdminFacade admin;
	
	@Autowired
	public CustomerFacade cust;
	
	@Autowired
	public CompanyFacade comp;
	
	@Autowired
	CompanyRepo cr;
	
	@Autowired
	CustomerRepo custRep;
	
	@Autowired
	CouponRepo coupRep;
	
	@Test
	public void contextLoads() {
		
	}
	
	// !!!!! PROPERTIES!!!!!!: spring.jpa.hibernate.ddl-auto=update   (Should be UPDATE)
	
	
	
	//CompanyFacade functions:
	
	
	
	/**
	 * Remove coupon
	 */
	@Test
	public void removeCoupon() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 comf.removeCoupon(cr.getCouponByTitle("Title3", cr.findCompByName("Comp1").getId()));
		Assert.assertNull(cr.getCouponByTitle("Title3", cr.findCompByName("Comp1").getId()));
	}
	
	/**
	 * Update coupon
	 */
	@Test
	public void updateCoupon() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 Coupon c = cr.getCouponByTitle("Title2", cr.findCompByName("Comp1").getId());
		 c.setAmount(1000);
		 comf.updateCoupon(c);
		Assert.assertEquals(1000, coupRep.findOne(c.getId()).getAmount());
	}
	
	/**
	 * Get coupon
	 */
	@Test
	public void getCoupon() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 Coupon c = comf.getCoupon(cr.getCouponByTitle("Title2", cr.findCompByName("Comp1").getId()).getId());
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get coupon by title
	 */
	@Test
	public void getCouponByTitle() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 Coupon c = comf.getCouponByTitle("Title2");
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get all coupons
	 */
	@Test
	public void getAllCoupons() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		ArrayList<Coupon> c = comf.getAllCoupons();
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get all coupons by type
	 */
	@Test
	public void getAllCouponsByType() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		ArrayList<Coupon> c = comf.getCouponsByType(CouponType.FOOD);
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get all coupons by price
	 */
	@Test
	public void getAllCouponsByPrice() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		ArrayList<Coupon> c = comf.getCouponsByPrice(20.5d);
		Assert.assertNotNull(c);
	}
	
	
	//CustomerFacade functions:
	
	/**
	 * purchase coupon
	 */
	@Test
	public void purchaseCoup()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		cf.purchaseCoupon(cr.getCouponByTitle("Title1", cr.findCompByName("Comp1").getId()));
		Coupon c =custRep.getPurchasedCouponByCustomer(custRep.getCustomerByName("Customer2").getId(),cr.getCouponByTitle("Title1", cr.findCompByName("Comp1").getId()).getId() );
		Assert.assertNotNull(c);
	}
	/**
	 * purchase coupon
	 */
	@Test
	public void purchaseCoup2()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		cf.purchaseCoupon(cr.getCouponByTitle("Title2", cr.findCompByName("Comp1").getId()));
		Coupon c =custRep.getPurchasedCouponByCustomer(custRep.getCustomerByName("Customer2").getId(),cr.getCouponByTitle("Title2", cr.findCompByName("Comp1").getId()).getId() );
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get purchased coupons
	 */
	@Test
	public void getPurchasedCoup()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		ArrayList<Coupon> c =cf.getPurchasedCoupons();
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get purchased coupon by type
	 */
	@Test
	public void getPurchasedCoupByType()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		ArrayList<Coupon> c =cf.getPurchaseCouponsByType(CouponType.FOOD);
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get purchased coupon by price
	 */
	@Test
	public void getPurchasedCoupByPrice()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		ArrayList<Coupon> c =cf.getPurchaseCouponsByPrice(20.5d);
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get  coupon
	 */
	@Test
	public void getpCoupon()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		Coupon c =cf.getCoupon(1);
		Assert.assertNotNull(c);
	}
}
