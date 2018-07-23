package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Repo.CompanyRepo;
import com.example.demo.Repo.CouponRepo;
import com.example.demo.Repo.CustomerRepo;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.enums.ClientType;
import com.example.demo.enums.CouponType;
import com.example.demo.facades.AdminFacade;
import com.example.demo.facades.CompanyFacade;
import com.example.demo.facades.CustomerFacade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests2 {


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
	
	
	// !!!!! PROPERTIES!!!!!!: spring.jpa.hibernate.ddl-auto=update   (Should change to UPDATE)
	
	//AdminFacade Functions:
	
	
	
	/**
	 * removing company
	 */
	@Test
	public void removeComp()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.removeCompany(cr.findCompByName("Comp3"));
		Assert.assertNull(cr.findCompByName("Comp3"));
	}
	
	/**
	 * update company
	 */
	@Test
	public void updateComp()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Company c=cr.findCompByName("Comp2");
		c.setPass("87654");
		af.updateCompany(c);
		Assert.assertEquals("87654", cr.findCompByName("Comp2").getPass());
	}
	
	/**
	 * Get company
	 */
	@Test
	public void getComp()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Company c = af.getCompany(cr.findCompByName("Comp2").getId());
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get company by name
	 */
	@Test
	public void getCompByName()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Company c = af.getCompanyByName("Comp2");
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get all companies
	 */
	@Test
	public void getAllComp()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		ArrayList<Company> allComp = af.getAllCompanies();
		Assert.assertNotNull(allComp);
	}
	
	
	
	/**
	 * Remove customer
	 */
	@Test
	public void removeCustomer()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.removeCustomer(custRep.getCustomerByName("Customer3"));
		Assert.assertNull(custRep.getCustomerByName("Customer3"));
	}
	
	/**
	 * Update customer
	 */
	@Test
	public void updateCustomer()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Customer c=custRep.findOne(custRep.getCustomerByName("Customer2").getId());
		c.setPass("9999999999999");
		af.updateCustomer(c);
		Assert.assertEquals("9999999999999", custRep.getCustomerByName("Customer2").getPass());
	}
	
	/**
	 * Get customer
	 */
	@Test
	public void getCustomer()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Customer c =af.getCustomer(custRep.getCustomerByName("Customer2").getId());
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get customer by name
	 */
	@Test
	public void getCustomerByName()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Customer c =af.getCustomerByName("Customer2");
		Assert.assertNotNull(c);
	}
	
	/**
	 * Get all customers
	 */
	@Test
	public void getAllCustomers()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		ArrayList<Customer>  c =af.getAllCustomers();
		Assert.assertNotNull(c);
	}
	
	//CompanyFacade functions:
	
	
	/**
	 * Create coupon
	 * @exception ParseException 
	 */
	@Test
	public void CreateCoupon() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 Date endDate=new Date();
		 SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		 try {
			endDate = format.parse("01.01.2019");
		} catch (ParseException e) {
			System.out.println("invalid format of date");
		}
		comf.createCoupon(new Coupon("absd", new Date(), endDate, 10, CouponType.FOOD, "blabla", 20.5d, "path"));
		Assert.assertNotNull(cr.getCouponByTitle("absd", cr.findCompByName("Comp1").getId()));
	}
	/**
	 * Create coupon
	 * @exception ParseException 
	 */
	@Test
	public void CreateCoupon2() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 Date endDate=new Date();
		 SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		 try {
			endDate = format.parse("01.01.2019");
		} catch (ParseException e) {
			System.out.println("invalid format of date");
		}
		comf.createCoupon(new Coupon("Title2", new Date(), endDate, 10, CouponType.FOOD, "blabla", 20.5d, "path"));
		Assert.assertNotNull(cr.getCouponByTitle("Title2", cr.findCompByName("Comp1").getId()));
	}
	/**
	 * Create coupon
	 * @exception ParseException 
	 */
	@Test
	public void CreateCoupon3() 
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		 Date endDate=new Date();
		 SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		 try {
			endDate = format.parse("01.01.2019");
		} catch (ParseException e) {
			System.out.println("invalid format of date");
		}
		comf.createCoupon(new Coupon("Title3", new Date(), endDate, 10, CouponType.FOOD, "blabla", 20.5d, "path"));
		Assert.assertNotNull(cr.getCouponByTitle("Title3", cr.findCompByName("Comp1").getId()));
	}

}


