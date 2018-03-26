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
public class CouponSystemApplicationTests {


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
	
	
	//AdminFacade Functions:
	
	/**
	 * Login as admin
	 */
	@Test
	public void adminLogin()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Assert.assertNotNull(af);
	}
	/**
	 * Login as admin with wrong password
	 */
	@Test
	public void adminLogin2()
	{
		AdminFacade af = admin.login("admin", "4321", ClientType.ADMIN);
		Assert.assertNull(af);
	}
	/**
	 * Creating company
	 */
	@Test
	public void createComp()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCompany(new Company("Comp1", "12345", "a@gmail.com"));
		Assert.assertNotNull(cr.findCompByName("Comp1"));
	}
	
	/**
	 * Creating company
	 */
	@Test
	public void createComp2()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCompany(new Company("Comp2", "12345", "a@gmail.com"));
		Assert.assertNotNull(cr.findCompByName("Comp2"));
	}
	/**
	 * Creating company
	 */
	@Test
	public void createComp3()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCompany(new Company("Comp3", "12345", "a@gmail.com"));
		Assert.assertNotNull(cr.findCompByName("Comp3"));
	}
	
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
	 * Creating customer
	 */
	@Test
	public void createCustomer()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCustomer(new Customer("Customer1", "4321"));
		Assert.assertNotNull(custRep.getCustomerByName("Customer1"));
	}
	/**
	 * Creating customer
	 */
	@Test
	public void createCustomer2()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCustomer(new Customer("Customer2", "4321"));
		Assert.assertNotNull(custRep.getCustomerByName("Customer2"));
	}
	/**
	 * Creating customer
	 */
	@Test
	public void createCustomer3()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCustomer(new Customer("Customer3", "4321"));
		Assert.assertNotNull(custRep.getCustomerByName("Customer3"));
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
	 * Login as Company
	 */
	@Test
	public void companyLogin()
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		Assert.assertNotNull(comf);
	}
	
	/**
	 * Login as Company with wrong pass
	 */
	@Test
	public void companyLogin2()
	{
		CompanyFacade comf = comp.login("Comp1", "43621", ClientType.COMPANY);
		Assert.assertNull(comf);
	}
	
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
		comf.createCoupon(new Coupon("Title1", new Date(), endDate, 10, CouponType.FOOD, "blabla", 20.5d, "path"));
		Assert.assertNotNull(cr.getCouponByTitle("Title1", cr.findCompByName("Comp1").getId()));
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
	 * Login as Customer
	 */
	@Test
	public void customerLogin()
	{
		CustomerFacade cf = cust.login("Customer2", "9999999999999", ClientType.CUSTOMER);
		Assert.assertNotNull(cf);
	}
	
	/**
	 * Login as Customer with wrong pass
	 */
	@Test
	public void customerLogin2()
	{
		CustomerFacade cf = cust.login("Customer2", "43621", ClientType.CUSTOMER);
		Assert.assertNull(cf);
	}
	
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


